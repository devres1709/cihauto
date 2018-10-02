public class Operation {

    private String name;
    private LogicClass logicClass;

    public LogicClass getLogicClass() {
        return logicClass;
    }

    public void setLogicClass(LogicClass logicClass) {
        this.logicClass = logicClass;
    }

    public Operation(String name, LogicClass logicClass) {
        this.name = name;
        this.logicClass = logicClass;
    }

    private static String inputProp = "<Input Name=\"{INPUT}\" Description=\"\" Usage=\"Optional\" DefaultValue=\"\" DataType=\"ru.atc.cih.datatype.general.{INPUT}\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" ExternalType=\"false\">";
    //insert {INPUT_PROP}
    private String doInputsBlock(){
        return  propBlockBuilder(DataType.Type.INPUT);
    }

    private static String outputProp = "<Output Name=\"{OUTPUT}\" Description=\"\" Usage=\"Optional\" DefaultValue=\"\" DataType=\"ru.atc.cih.datatype.general.{OUTPUT}\" CustomizationLevel=\"1\" BaseCustomizationLevel=\"1\" ExternalType=\"false\" SimulationExpr=\"\" AsyncOutput=\"false\">";
    //{OUTPUT_PROP}
    private String doOutputBlock(){
        return  propBlockBuilder(DataType.Type.OUTPUT);
    }

    private  String propBlockBuilder(DataType.Type type){
        StringBuilder block = new StringBuilder();
        for(DataType dt : logicClass.getFields()) {
            if(dt.getType() == type) {
                block.append(inputProp.replace("{"+ type.name().toUpperCase() +"}", dt.getName())
                                      .replace("{"+ type.name().toUpperCase() +"}", dt.getName()) + "\n");
                block.append("\t\t" + "<Properties />");
            }
        }
        return block.toString();

    }





}
