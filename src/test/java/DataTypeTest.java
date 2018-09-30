

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class DataTypeTest {

    @Test
    public void doXmlTest1() throws Exception {
        String type = "String String String String String String";
        String prop = "srls_id serv_first_id srls_id_name current_virtual_number_attribute pack_id count_svc_pack";
        DataType dataTypeCreateor = new DataType("PromoEquipSvcInfo", prop, type);
        assertTrue(compareResult("src\\test\\resources\\PromoEquipSvcInfo.type", "src\\test\\resourcesAfterTest\\XPromoEquipSvcInfo.type"));
        File file = new File("src\\test\\resourcesAfterTest\\XPromoEquipSvcInfo.type");
        System.out.println(file.delete());
    }

    @Test
    public void doXmlTest2() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology";
        DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type);
        assertTrue(compareResult("src\\test\\resourcesAfterTest\\XCeaseOrderActionsInfoTest.type", "src\\test\\resources\\XCeaseOrderActionsInfo.type"));
        File file = new File("src\\test\\resourcesAfterTest\\CeaseOrderActionsInfoTest.type");
        file.delete();
    }


    @Test
    public void doXmlWrongTest() throws Exception {
        String type = "String String String String";
        String prop = "OrderId ApId Lob Technology arg3";
        boolean error = false;
        try {
            DataType dataType = new DataType("CeaseOrderActionsInfoTest", prop, type);
        }catch (Exception e) {
            error = true;
        }
        finally {
            assertTrue(error);
        }
    }






    private String stringFromFileWithoutSpaces(Scanner scanner){
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext()){
            result.append(scanner.nextLine());
        }
        return result.toString().replace(" ", "").replace("\\n", "");
    }

    private boolean compareResult(String pathToFileResultOfWork, String pathToStandert) throws FileNotFoundException {
        String resultOfWork = stringFromFileWithoutSpaces(new Scanner(new File(pathToFileResultOfWork)));
        String standart = stringFromFileWithoutSpaces(new Scanner(new File(pathToStandert)));
        System.out.println(standart + "\n" + resultOfWork);
        return resultOfWork.equals(standart);
    }


}