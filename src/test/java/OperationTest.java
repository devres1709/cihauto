
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OperationTest {

    @Test
    public void doXmlTest() throws Exception {
        String propForFirstDT = "myIntType myBooleanType";
        String typesForFirstDT = "int boolean";
        String propForSecondDT = "MyStrinField";
        String typeForSecondDT = "String";
        DataType firstDT = new DataType("MyTestInput", propForFirstDT, typesForFirstDT, DataType.Type.INPUT);
        DataType secondDT = new DataType("MyTestOutput", propForSecondDT, typeForSecondDT, DataType.Type.OUTPUT);
        List<DataType> dt = new ArrayList<DataType>();
        dt.add(firstDT);
        dt.add(secondDT);
        LogicClass logicClass = new LogicClass("XMyTestClassImpl", dt);


        Operation op = new Operation("XMyTestClass", logicClass);

        Assert.assertTrue(TestUtils.compareResult("src\\test\\resourcesAfterTest\\XMyTestClass.oper",
                "src\\test\\resources\\XMyTestClass.oper"));


    }

}
