import Impl.LogicClass;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ApmClassAdder {

    private static Map<String, String> apmParam;
    static {
        apmParam = new LinkedHashMap<String, String>();
        apmParam.put("AllowRemove", "true");
        apmParam.put("IsRootTarget", "false");
        apmParam.put("Predefined", "false");
        apmParam.put("Recurse", "true;");
        apmParam.put("Name",
                "\"${SourceRootDir}/ru/atc/oms/cih/services/orderingactivities/{CLASS_IMPL}.java\"");
        apmParam.put("Dir", "\"${SourceRootDir}/ru/atc/oms/cih/services/orderingactivities\"");
        apmParam.put("IsGenerated", "false;");
        apmParam.put("Filename", "\"{CLASS_IMPL}\"");
        apmParam.put("SaveVersions","true");
        apmParam.put("PackageName", "\"ru.atc.oms.cih.services.orderingactivities");
        apmParam.put("generatedOnly", "false");
        apmParam.put("needsSourcePath", "true");
        apmParam = Collections.unmodifiableMap(apmParam);
    }

    private LogicClass logicClass;

    private void addClass(){
        StringBuilder sb = new StringBuilder();
        String id = UUID.randomUUID().toString().substring(30);
        String template = "JavaSourceFileTarget:\"@JavaSourceFileTarget@" + id + "\".";
        for(Map.Entry<String, String> entry : apmParam.entrySet()){
            if(entry.getKey().equals("Name") || entry.getKey().equals("Filename")){
                sb.append(template + entry.getKey() + " := " + entry.getValue().replace("{CLASS_IMPL}", logicClass.getName()) + ";\n");
            }
            else
                sb.append(template + entry.getKey() + " := " + entry.getValue() + ";\n");
        }


    }






}
