import java.io.*;
import java.util.List;
import java.util.Scanner;

public class LogicClass {
    List<DataType> fields;
    String name;
    private static String packagePath = "import ru.atc.cih.datatype.general.{DATATYPENAME}";

    public LogicClass(String name, List<DataType> fields) throws IOException {
        nameValid(name);
        if(fields == null)
            return;
        this.fields = fields;
        doClass();
    }

    public List<DataType> getFields() {
        return fields;
    }

    public void setFields(List<DataType> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        nameValid(name);
    }

    private void nameValid(String name) {
        StringBuffer tempName = new StringBuffer(name);
        if(name.startsWith("xX") || name.startsWith("xx")){
            tempName.replace(0, 1, "X");
            name = tempName.toString();
        }
        if(name.startsWith("x") || name.startsWith("xx")) {
            tempName.replace(0, 1, "X");
            name = tempName.toString();
        }
        if (!name.startsWith("X")) {
            tempName.insert(0, "X");
            name = tempName.toString();
        }
        if (!name.endsWith("Impl")){
            tempName.insert(name.length(), "Impl");
            name = tempName.toString();
        }
        this.name = tempName.toString();
    }

    private String contextForClass() throws FileNotFoundException {
        StringBuilder classContent = new StringBuilder();
        Scanner scanner = new Scanner(new File("src\\main\\resources\\classTemplate.java"));
        while (scanner.hasNext())
            classContent.append(scanner.nextLine() + "\n");

        String result = classContent.toString().replace("{CLASSNAME}", name)
                .replace("{DATATYPENAME}", getPackagePath())
                .replace("{DATATYPIES}", generateFields())
                .replace("{GETTERS}", genereteGettes())
                .replace("{SETTERS}", generateSetters());
        return result;
    }

    private void doClass() throws IOException {
        String dir = "src\\test\\resourcesAfterTest\\";
        BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".java"));
        writer.write(contextForClass());
        writer.close();
    }

    //insert in {DATATYPENAME}
    private String getPackagePath(){
        StringBuilder result = new StringBuilder();
        for (DataType dt : fields)
            result.append("\n" + packagePath.replace("{DATATYPENAME}", dt.getName() + ";"));
        return result.toString();
    }

    //insert in {DATATYPIES}
    private String generateFields(){
        StringBuilder result = new StringBuilder();
        for(DataType dt : fields)
            result.append("private " + dt.getName() + " " + doFieldName(dt.getName()) + ";\n\t");
        return result.toString();
    }

    private static String doFieldName(String className){
       StringBuilder sb = new StringBuilder(className);
       sb.replace(0, 1, (className.charAt(0) + "").toLowerCase());
       return sb.toString();
    }
    //insert {GETTERS}
    private String genereteGettes(){
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for(DataType dt : fields) {
            result.append("\tpublic " + dt.getName() + " get" + dt.getName() + "() {");
            result.append(" return " + doFieldName(dt.getName()) + "; }\n\n");
        }
        return result.toString();
    }
    //{SETTERS}
    private String generateSetters(){
        StringBuilder result = new StringBuilder();
        for(DataType dt : fields) {
            result.append("public void set" + dt.getName());
            result.append( "(" + dt.getName() + " "  + doFieldName(dt.getName() + "){\n"));
            result.append("\t\tthis." + doFieldName(dt.getName()) + " = " + doFieldName(dt.getName() + ";\n"));
            result.append("\t}\n\t");
        }
        return result.toString();
    }
}


