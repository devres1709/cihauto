import Impl.DataType;
import Impl.LogicClass;
import Impl.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

    private final static String PATH_TO_IMPL_CLASS = "C:\\OMS\\EclipseWS\\cord9deploy\\v81_10\\application_server\\src\\ru\\atc\\oms\\cih\\services\\orderingactivities";

    enum OperName {
        DO_PROPERTY_NAME,
        DO_PROPERTY_TYPE,
    }

    private static String lineReader(){
        String result = in.nextLine();
        while(result.equals(""))
            result = in.nextLine();
        return result;
    }

    private static Scanner in = new Scanner(System.in);

    private static void doFieldsForDataType(OperName oper, StringBuilder input){
        switch (oper){
            case DO_PROPERTY_NAME:
                System.out.print("Name :");
                break;
            case DO_PROPERTY_TYPE:
                System.out.print("Type : ");
                break;

        }
        String temp = lineReader();
        input.append(temp.trim() + " ");
    }



    private static void formFieldDetails(StringBuilder names, StringBuilder types){
        while (true){
            System.out.println("Enter field details: ");
            doFieldsForDataType(OperName.DO_PROPERTY_NAME, names);
            doFieldsForDataType(OperName.DO_PROPERTY_TYPE, types);
            System.out.print("Add another field?(Y/N) : ");

            if(!loopBreaker())
                break;

        }
    }

    private static boolean loopBreaker(){
        String answer = lineReader();
        return answer.toLowerCase().trim().equals("y") || answer.trim().equals("yes");
    }

    private static DataType.Type typeSelector(){
        System.out.println("Enter type of AIF data type: ");
        System.out.println("1 - INPUT");
        System.out.println("2 - OUTPUT");
        final String WRONG = "Wrong input! Try again : ";
        while(true){
            String type = lineReader();
                if(type.trim().equals("1"))
                    return DataType.Type.INPUT;
                else if(type.trim().equals("2"))
                    return DataType.Type.OUTPUT;
                else
                    System.out.print(WRONG);
        }
    }


    private String getInfoAboitItemsOfCih(Operation oper){
        return null;
    }


    private static void doDataTypes(List<DataType> dataTypeList) throws Exception {

        while(true){
            StringBuilder propName = new StringBuilder();
            StringBuilder propType = new StringBuilder();
            System.out.print("Enter name of AIF data type : ");
            String dataTypeName = lineReader();
            DataType.Type type = typeSelector();
            formFieldDetails(propName, propType);
            dataTypeList.add(new DataType(dataTypeName, propName.toString(), propType.toString(), type));

            System.out.print("Add another AIF data type(Y/N) : ");
            if(!loopBreaker())
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("CIHAuto 1.0");
        List<DataType> dataTypes = new ArrayList<DataType>();
        doDataTypes(dataTypes);
        System.out.print("Enter name of class that implement of logic : ");
        LogicClass logicClass = new LogicClass(lineReader(), dataTypes);
        System.out.print("Enter name of AIF operation : ");
        Operation operation = new Operation(lineReader(), logicClass);

        if(!ProjectBuilder.build("buildCih")){
            System.out.println("WRONG!!! BUILD CIH FAILED!!!");
            System.out.println("All files in project: ");

            return;
        }


        System.out.println("add " + operation.getLogicClass().getName() + " .java in APM from:\n " + PATH_TO_IMPL_CLASS);
        System.out.print("Has class been added in APM(Y/N) : ");

        if(loopBreaker())
            if(!ProjectBuilder.build("buildOmsAll")){
                System.out.println("WRONG!!! BUILD OMS FAILED!!!");
            }
        else
            System.out.println("You need add class to APM an do build OMS");
    }
}
