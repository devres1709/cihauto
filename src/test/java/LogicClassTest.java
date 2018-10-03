import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        String propForFirstDT = "myIntType myBooleanType";
        String typesForFirstDT = "int boolean";
        String propForSecondDT = "MyStrinField";
        String typeForSecondDT = "String";
        DataType firstDT = new DataType("MyTestInput", propForFirstDT, typesForFirstDT, DataType.Type.INPUT);
        DataType secondDT = new DataType("MyTestOutput", propForSecondDT, typeForSecondDT, DataType.Type.OUTPUT);
        List<DataType>dt = new ArrayList<DataType>();
        dt.add(firstDT);
        dt.add(secondDT);
        LogicClass logicClass = new LogicClass("XMyTestClassImpl", dt);

        assertTrue(TestUtils.compareResult(
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resources\\XMyTestClassImpl.java")),
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resourcesAfterTest\\XMyTestClassImpl.java"))
        ));
        System.out.println("Class Ok!");
        assertTrue(TestUtils.compareResult(
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resources\\MyTestInput.type")),
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resourcesAfterTest\\MyTestInput.type"))
        ));
        System.out.println("XML 1 Ok!");
        assertTrue(TestUtils.compareResult(
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resources\\MyTestOutput.type")),
                TestUtils.stringFromFileWithoutSpaces(new Scanner("src\\test\\resourcesAfterTest\\MyTestOutput.type"))
        ));
        System.out.println("XML 2 Ok!");
    }


}