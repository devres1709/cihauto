

import Impl.DataType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.*;

public class DataTypeTest {

    private static String TEST_DIR = "src\\test\\resourcesAfterTest\\";

    @Before
    public void createResourcesAfterTestDir() throws InterruptedException {
        if (new File(TEST_DIR).mkdir())
            System.out.println("resourcesAfterTest has been created");

    }

    @Test
    public void doXmlTest1() throws Exception {
        String type = "String String String String String String";
        String prop = "srls_id serv_first_id srls_id_name current_virtual_number_attribute pack_id count_svc_pack";
        DataType dataTypeCreateor = new DataType("PromoEquipSvcInfo", prop, type, DataType.Type.INPUT);
        dataTypeCreateor.doFile(TEST_DIR);
        String newName = dataTypeCreateor.getName();
        assertEquals("PromoEquipSvcInfoInput", newName);
        assertTrue(TestUtils.compareResult("src\\test\\resources\\PromoEquipSvcInfo.type",
                "src\\test\\resourcesAfterTest\\" + newName + ".type"));
    }

    @Test
    public void doXmlTest2() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology";
        DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type, DataType.Type.INPUT);
        dataType.doFile(TEST_DIR);
        assertTrue(TestUtils.compareResult("src\\test\\resourcesAfterTest\\CeaseOrderActionsInfoTestInput.type",
                "src\\test\\resources\\XCeaseOrderActionsInfo.type"));
    }


    @Test
    public void doXmlWrongTest() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology arg3";
        boolean error = false;
        try {
            DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type, DataType.Type.INPUT);
        } catch (Exception e) {
            error = true;
        } finally {
            assertTrue(error);
        }
    }

    @Test
    public void doXmlTest() throws Exception {
        String type = "int boolean";
        String prop = "myIntType myBooleanType";
        DataType dataType = new DataType("MyTestInput", prop, type, DataType.Type.INPUT);
        dataType.doFile(TEST_DIR);
        String newName = dataType.getName();
        assertEquals("MyTestInput", newName);
        assertTrue(TestUtils.compareResult("src\\test\\resources\\MyTestInput.type",
                "src\\test\\resourcesAfterTest\\" + newName + ".type"));
    }


    @After
    public void deleteResourceAfterTest() throws IOException {
        deleteDirectory(new File(TEST_DIR + "\\"));
    }

    void deleteDirectory(File file) throws IOException {
        File f = new File(TEST_DIR);

        int limit = 20; // На всякий случай будем пробовать удалить только 5 секунд, чтоб не зациклить
        while (!f.delete() && limit > 0) {
            synchronized (this) {
                try {
                    this.wait(250); // Ждем 250 миллисекунд
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            limit--;
        }

    }
}