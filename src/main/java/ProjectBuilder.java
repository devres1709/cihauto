import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProjectBuilder {

    public static void build() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("C:\\scripts\\buildCih.bat");


        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new  InputStreamReader(proc.getErrorStream()));

        PrintStream ps = new PrintStream(System.out, true, "UTF-8");

        ps.println("Here is the standard output of the command:\n");
        String result;
        while ((result = stdInput.readLine()) != null)
            ps.println(result);

        ps.println("Here is the standard error of the command (if any):\n");
        while ((result = stdError.readLine()) != null)
            ps.println(result);

        proc.waitFor();







    }

}
