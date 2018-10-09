

import Impl.DataType;
import org.junit.Test;

import java.io.File;
import java.util.UUID;


import static org.junit.Assert.*;

public class DataTypeTest {

    @Test
    public void doXmlTest1() throws Exception {
        String type = "String String String String String String";
        String prop = "srls_id serv_first_id srls_id_name current_virtual_number_attribute pack_id count_svc_pack";
        DataType dataTypeCreateor = new DataType("PromoEquipSvcInfo", prop, type, DataType.Type.INPUT);
        String newName = dataTypeCreateor.getName();
        assertEquals("PromoEquipSvcInfoInput", newName);
        assertTrue(TestUtils.compareResult("src\\test\\resources\\PromoEquipSvcInfo.type",
                "src\\test\\resourcesAfterTest\\" + newName +".type"));


    }

    @Test
    public void doXmlTest2() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology";
        DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type, DataType.Type.OUTPUT);
        assertTrue(TestUtils.compareResult("src\\test\\resourcesAfterTest\\XCeaseOrderActionsInfoTest.type",
                "src\\test\\resources\\XCeaseOrderActionsInfo.type"));
        File file = new File("src\\test\\resourcesAfterTest\\CeaseOrderActionsInfoTest.type");
        file.delete();
    }


    @Test
    public void doXmlWrongTest() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology arg3";
        boolean error = false;
        try {
            DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type, DataType.Type.INPUT);
        }catch (Exception e) {
            error = true;
        }
        finally {
            assertTrue(error);
        }
    }

    @Test
    public void doXmlTest() throws Exception {
        String type = "int boolean";
        String prop = "myIntType myBooleanType";
        DataType dataType = new DataType("MyTestInput", prop, type, DataType.Type.INPUT);
        String newName = dataType.getName();
        assertEquals("MyTestInput", newName);
        assertTrue(TestUtils.compareResult("src\\test\\resources\\MyTestInput.type",
                "src\\test\\resourcesAfterTest\\" + newName +".type"));
    }






}