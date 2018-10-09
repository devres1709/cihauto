package Impl;

import Interfaces.FileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * class Impl.Operation implement generation of XML-file with extension .oper
 */
public class Operation implements FileCreator {

    private String name;
    private LogicClass logicClass;
    /*
    <ParameterOrder>
        ...
        <Param name="Key" index="Value" />
         ...
    </ParameterOrder>
     */
    private Map<String, Integer> parameter = new HashMap<String, Integer>();

    public LogicClass getLogicClass() {
        return logicClass;
    }
    public void setLogicClass(LogicClass logicClass) {
        this.logicClass = logicClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Integer> parameter) {
        this.parameter = parameter;
    }

    public Operation(String name, LogicClass logicClass) {
        this.name = name;
        this.logicClass = logicClass;
        doFile();
    }

    private static String propertyInput = "<Input Name=\"{TYPE}\" Description=\"\" Usage=\"Optional\" DefaultValue=\"\" DataType=\"ru.atc.cih.common.datatypes.{TYPE}\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" ExternalType=\"false\">";
    private static String propertyOutput = "<Output Name=\"{TYPE}\" Description=\"\" Usage=\"Optional\" DefaultValue=\"\" DataType=\"ru.atc.cih.common.datatypes.{TYPE}\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" ExternalType=\"false\" SimulationExpr=\"\" AsyncOutput=\"false\">";
    private static String parameterOrder = "<Param name=\"{INPUT}\" index=\"{INDEX}\" />";

    //insert in {INPUT_PROP}
    private String doInputsBlock() {
        return propBlockBuilder(DataType.Type.INPUT);
    }

    //insert in {OUTPUT_PROP}
    private String doOutputBlock() {
        return propBlockBuilder(DataType.Type.OUTPUT);
    }

    private String propBlockBuilder(DataType.Type type) {
        StringBuilder block = new StringBuilder();
        String property;
        if(type == DataType.Type.INPUT)
            property = propertyInput;
        else
            property = propertyOutput;

        for (DataType dt : logicClass.getFields()) {
            if (dt.getType() == type) {
                block.append(property.replace("{TYPE}", dt.getName()
                        .replace("{" + type.name().toUpperCase() + "}", dt.getName()) + "\n"));
                String tag = (type.name().charAt(0)) + type.name().substring(1).toLowerCase();
                block.append("\t\t" + "<Properties />" + "\n\t</" + tag +">");
            }
        }
        return block.toString();
    }


    //insert in {INPUT_PARAM}
    private String doParameterOrderBlock() {
        int index = 3;
        StringBuilder sb = new StringBuilder();
        for (DataType dt : logicClass.getFields()) {
            if(dt.getType() == DataType.Type.INPUT) {
                parameter.put(dt.getName(), index);
                sb.append(parameterOrder.replace("{INPUT}", dt.getName())
                        .replace("{INDEX}", index + "") + "\n\t\t");
                index++;
            }
        }
        return sb.toString();
    }


    /**
     * This method implement generation of XML-file
     * and save it in ru.atc.cih.common.datatypes
     * @return true - if write is complete successfully
     */
    public boolean doFile(){
        try {
            Scanner scanner = new Scanner(new File("src\\main\\resources\\templates\\oper.xml"));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine())
                sb.append(scanner.nextLine() + "\n");

            String dir = "C:\\OMS\\EclipseWS\\cord9aif\\v81_10\\cih\\repository\\com\\amdocs\\cih\\services\\oms\\interfaces\\IOmsServices\\"; //"src\\test\\resourcesAfterTest\\";
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".oper"));
            writer.write(sb.toString().replace("{INPUT_PROP}", doInputsBlock())
                    .replace("{OUTPUT_PROP}", doOutputBlock())
                    .replace("{INPUT_PARAM}", doParameterOrderBlock())
                    .replace("{OPER_NAME}", name));
            writer.flush();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


}
