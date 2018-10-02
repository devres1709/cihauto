import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CihBuilder {

    public static void build() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("ping crmdev");
        proc.waitFor();
        proc.destroy();

//        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//        BufferedReader stdError = new BufferedReader(new  InputStreamReader(proc.getErrorStream()));


        System.out.println("Here is the standard output of the command:\n");
        String result;
        while ((result = stdInput.readLine()) != null)
            System.out.println(result);

        System.out.println("Here is the standard error of the command (if any):\n");
        while ((result = stdError.readLine()) != null)
            System.out.println(result);





    }

}
