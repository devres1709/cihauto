package Impl;

import Interfaces.FileCreator;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * class Impl.LogicClass implement generation of java class, witch
 * extends CihStatelessServiceBase and implement methods of
 * CihStatelessServiceBase. Also this class generation getters and
 * setter from List<Impl.DataType> fields;
 */

public class LogicClass implements FileCreator {
    List<DataType> fields;
    String name;
    private static String packagePath = "import ru.atc.cih.common.datatypes.{DATATYPENAME}";
    private static String DEFAULT_PATH = "C:\\OMS\\EclipseWS\\cord9deploy\\v81_10\\application_server\\src\\ru\\atc\\oms\\cih\\services\\orderingactivities\\";
    private static String PATH_TO_TEMPLATE = "src\\main\\resources\\templates\\classTemplate.java";

    public LogicClass(String name, List<DataType> fields) throws IOException {
        nameValid(name);
        if (fields == null)
            return;
        this.fields = fields;
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

    /**
     * private void nameValid(String name) implement name
     * verification for valid
     *
     * @param name -correct name
     */
    private void nameValid(String name) {
        StringBuffer tempName = new StringBuffer(name);
        if (name.startsWith("xX") || name.startsWith("xx")) {
            tempName.replace(0, 1, "X");
            name = tempName.toString();
        }
        if (name.startsWith("x") || name.startsWith("xx")) {
            tempName.replace(0, 1, "X");
            name = tempName.toString();
        }
        if (!name.startsWith("X")) {
            tempName.insert(0, "X");
            name = tempName.toString();
        }
        if (!name.endsWith("Impl")) {
            tempName.insert(name.length(), "Impl");
            name = tempName.toString();
        }
        this.name = tempName.toString();
    }

    /**
     * private String contextForClass() load template
     *
     * @return string template
     * @throws FileNotFoundException
     */
    private String contextForClass() throws FileNotFoundException {
        StringBuilder classContent = new StringBuilder();
        Scanner scanner = new Scanner(new File(PATH_TO_TEMPLATE));
        while (scanner.hasNext())
            classContent.append(scanner.nextLine() + "\n");

        String result = classContent.toString().replace("{CLASSNAME}", name)
                .replace("{DATATYPENAME}", getPackagePath())
                .replace("{DATATYPIES}", generateFields())
                .replace("{GETTERS}", genereteGettes())
                .replace("{SETTERS}", generateSetters());
        return result;
    }

    @Override
    public boolean doFile(String pathToDir) {
        try {
            //"src\\test\\resourcesAfterTest\\"; // - for test
            String dir;
            if (pathToDir == null)
                dir = DEFAULT_PATH;
            else
                dir = pathToDir;

            BufferedWriter writer = new BufferedWriter(new FileWriter(dir + name + ".java"));
            writer.write(contextForClass());
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method implement generation of XML-file
     * and save it in ru.atc.oms.cih.services.orderingactivities
     * @return true - if write is complete successfully
     */
    public boolean doFile() {
        return doFile(null);
    }


    //insert in {DATATYPENAME}
    private String getPackagePath() {
        StringBuilder result = new StringBuilder();
        for (DataType dt : fields)
            result.append("\n" + packagePath.replace("{DATATYPENAME}", dt.getName() + ";"));
        return result.toString();
    }

    //insert in {DATATYPIES}
    private String generateFields() {
        StringBuilder result = new StringBuilder();
        for (DataType dt : fields)
            result.append("private " + dt.getName() + " " + doFieldName(dt.getName()) + ";\n\t");
        return result.toString();
    }

    /**
     * private static String doFieldName(String className) it's
     * like string formater:
     * <p>
     * input: XMyTestInput
     * output: xMyTestInput
     *
     * @param className - name of class field
     * @return
     */
    private static String doFieldName(String className) {
        StringBuilder sb = new StringBuilder(className);
        sb.replace(0, 1, (className.charAt(0) + "").toLowerCase());
        return sb.toString();
    }

    //insert in {GETTERS}
    private String genereteGettes() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (DataType dt : fields) {
            result.append("\tpublic " + dt.getName() + " get" + dt.getName() + "() {");
            result.append(" return " + doFieldName(dt.getName()) + "; }\n\n");
        }
        return result.toString();
    }

    //insert in {SETTERS}
    private String generateSetters() {
        StringBuilder result = new StringBuilder();
        for (DataType dt : fields) {
            result.append("public void set" + dt.getName());
            result.append("(" + dt.getName() + " " + doFieldName(dt.getName() + "){\n"));
            result.append("\t\tthis." + doFieldName(dt.getName()) + " = " + doFieldName(dt.getName() + ";\n"));
            result.append("\t}\n\t");
        }
        return result.toString();
    }
}


