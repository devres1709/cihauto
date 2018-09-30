import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LogicClassTest {

    @Test
    public void nameSetFromConstructor1() throws IOException {
        LogicClass logicClass = new LogicClass("Test", null);
        assertEquals("XTestImpl", logicClass.getName());
    }

    @Test
    public void nameSetFromConstructor2() throws IOException {
        LogicClass logicClass = new LogicClass("xTestImpl", null);
        assertEquals("XTestImpl", logicClass.getName());
    }

    @Test
    public void nameSetFromConstructor3() throws IOException {
        LogicClass logicClass = new LogicClass("xTestImpl", null);
        assertEquals("XTestImpl", logicClass.getName());
    }

    @Test
    public void nameSetFromConstructor4() throws IOException {
        LogicClass logicClass = new LogicClass("xXTest", null);
        assertEquals("XXTestImpl", logicClass.getName());
    }

    @Test
    public void nameSetFromConstructor5() throws IOException {
        LogicClass logicClass = new LogicClass("XMyTest", null);
        assertEquals("XMyTestImpl", logicClass.getName());
    }




    @Test
    public void doClassTest() throws Exception {
        String propForFirstDT = "OrderId MyIndex";
        String typesForFirstDT = "String Integer";

        String propForSecondDT = "CustonerId AddresId";
        String typeForSecondDT = "Long Long";

        DataType firstDT = new DataType("XMyFirstDataType", propForFirstDT, typesForFirstDT);
        DataType secondDT = new DataType("XMySecondDataType", propForSecondDT, typeForSecondDT);
        List<DataType>dt = new ArrayList<DataType>();
        dt.add(firstDT);
        dt.add(secondDT);

        LogicClass logicClass = new LogicClass("XMyTestImpl", dt);

    }





}