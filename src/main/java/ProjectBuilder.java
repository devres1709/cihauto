import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProjectBuilder {

    public static boolean build(String scriptName) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("C:\\scripts\\" + scriptName + ".bat");

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        PrintStream ps = new PrintStream(System.out, true, "UTF-8");

        String result;
        while ((result = stdInput.readLine()) != null) {
            if(result.contains("BUILD FAILED"))
                return false;
            ps.println(result);
        }
        proc.waitFor();
        return true;
    }

}
