import java.io.*;
import java.util.*;

public class DataType {

    public enum Type {
        INPUT,
        OUTPUT
    }

    private DataType.Type type;
    private String name;
    private Map<String, String> propNameAndType = new LinkedHashMap<String, String>();
    private static String template = "\n<Property Name=\"{PROPERTY_NAME}X1\" Version=\"2.0\" Description=\"\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" Type=\"{TYPE}\" Deprecated=\"false\" DeprecatedDescription=\"\" Release=\"\" InitMethod=\"\" InitValue=\"\" ReadOnly=\"false\" DirtySupported=\"false\" ReadOnlyByMe=\"false\" DeprecatedByMe=\"false\" />";

    private void doPropNameAndTypeMap(String prop, String type) throws Exception {
        List<String> keys = (Arrays.asList(prop.split(" ")));
        List<String> value = (Arrays.asList(type.split(" ")));
        if (keys.size() != value.size())
            throw new Exception("Wrong number of property or types!");
        for (int i = 0; i < keys.size(); i++)
            propNameAndType.put(keys.get(i), value.get(i));
    }


    public String getName() {
        return name;
    }

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
        doXml();
    }

    private boolean doXml() {
        try {
            StringBuilder userProp = new StringBuilder();
            for (Map.Entry<String, String> entry : propNameAndType.entrySet()) {
                userProp.append(template.replace("{PROPERTY_NAME}", entry.getKey())
                        .replace("{TYPE}", entry.getValue()));
            }
            userProp.append("\n");

            StringBuilder result = new StringBuilder();
            Scanner scanner = new Scanner(new File("src\\main\\resources\\datatype.xml"));
            //output dir
            String dir = "src\\test\\resourcesAfterTest\\";
            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".type"));
            while (scanner.hasNext())
                result.append(scanner.nextLine() + "\n");
            writer.append(result.toString().replace("{PROPERTIES}", userProp.toString()));
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


}
