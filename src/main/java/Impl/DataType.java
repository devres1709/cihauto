package Impl;

import Interfaces.FileCreator;

import java.io.*;
import java.util.*;

/**
 * class Impl.DataType implement generation of XML-file with extension .type
 */

public class DataType implements FileCreator {

    public enum Type {
        INPUT,
        OUTPUT
    }


    private DataType.Type type;
    private String name;
    private Map<String, String> propNameAndType = new LinkedHashMap<String, String>();
    private static String template = "\n<Property Name=\"{PROPERTY_NAME}X1\" Version=\"2.0\" Description=\"\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" Type=\"{TYPE}\" Deprecated=\"false\" DeprecatedDescription=\"\" Release=\"\" InitMethod=\"\" InitValue=\"\" ReadOnly=\"false\" DirtySupported=\"false\" ReadOnlyByMe=\"false\" DeprecatedByMe=\"false\" />";

    /**
     *
     * @param prop - name of field details
     * @param type - type of field details
     *
     *             prop and type to the entrance string in format:
     *             for prop - "MyStrinField MyIntField
     *             for type - "String int"
     * @throws Exception Wrong number of property or types!
     */
    private void doPropNameAndTypeMap(String prop, String type) throws Exception {
        List<String> keys = (Arrays.asList(prop.split(" ")));
        List<String> value = (Arrays.asList(type.split(" ")));
        if (keys.size() != value.size())
            throw new Exception("Wrong number of property or types!");
        for (int i = 0; i < keys.size(); i++)
            propNameAndType.put(keys.get(i), value.get(i));
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name - name of type
     */
    public void setName(String name) {
        switch (this.type) {
            case INPUT:
                if (!name.endsWith("Input"))
                    this.name = name + "Input";
                else
                    this.name = name;
                break;
            case OUTPUT:
                if (!name.endsWith("Output"))
                    this.name = name + "Output";
                else
                    this.name = name;
                break;
        }


    }

    public Map<String, String> getPropNameAndType() {
        return propNameAndType;
    }

    public void setPropNameAndType(Map<String, String> propNameAndType) {
        this.propNameAndType = propNameAndType;
    }

    public static String getTemplate() {
        return template;
    }

    public static void setTemplate(String template) {
        DataType.template = template;
    }

    public DataType(String name, String properties, String type, Type io) throws Exception {
        this.type = io;
        setName(name);
        doPropNameAndTypeMap(properties, type);
        doFile();
    }

    /**
     * This method implement generation of XML-file
     * and save it in ru.atc.cih.datatype.general
     * @return true - if write is complete successfully
     */
    public boolean doFile() {
        try {
            StringBuilder userProp = new StringBuilder();
            for (Map.Entry<String, String> entry : propNameAndType.entrySet()) {
                userProp.append(template.replace("{PROPERTY_NAME}", entry.getKey())
                        .replace("{TYPE}", entry.getValue()));
            }
            userProp.append("\n");

            StringBuilder result = new StringBuilder();
            Scanner scanner = new Scanner(new File("src\\main\\resources\\templates\\datatype.xml"));
            //output dir ru.atc.cih.datatype.general
            //String dir = "src\\test\\resourcesAfterTest\\"; // - test
            String dir = "C:\\OMS\\EclipseWS\\cord9aif\\v81_10\\cih\\repository\\ru\\atc\\cih\\common\\datatypes\\";
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".type"));
            while (scanner.hasNext())
                result.append(scanner.nextLine() + "\n");
            writer.append(result.toString().replace("{PROPERTIES}", userProp.toString()));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


}
