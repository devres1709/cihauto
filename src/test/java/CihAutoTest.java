import Impl.DataType;
import Impl.LogicClass;
import Impl.Operation;
import Interfaces.FileCreator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CihAutoTest {

    @Test
    public void testForFirsPartOfCIHAuto() throws Exception {

        String prop1 = "MyStringId";
        String prop2 = "MyStringOutId";
        String type = "String";

        FileCreator dataTypeInput = new DataType("CihAutoInput", prop1, type, DataType.Type.INPUT);
        FileCreator dataTypeOutput = new DataType("CihAutoOutput", prop2, type, DataType.Type.OUTPUT);

        List<DataType> dataTypeList = new ArrayList<DataType>();
        dataTypeList.add((DataType) dataTypeInput);
        dataTypeList.add((DataType) dataTypeOutput);

        FileCreator zlass = new LogicClass("XCihAutoImpl", dataTypeList);
        FileCreator oper = new Operation("XcihAutoOper", (LogicClass)zlass);
        ProjectBuilder.build("buildCih");

        System.out.println("add XCihAutoImpl.java in APM from:\n C:\\OMS\\EclipseWS\\cord9deploy\\v81_10\\application_server\\src\\ru\\atc\\oms\\cih\\services\\orderingactivities");

        System.out.println("After adding of class in APM enter anywhere text, and press ENTER");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        ProjectBuilder.build("buildOmsAll");





    }


}
