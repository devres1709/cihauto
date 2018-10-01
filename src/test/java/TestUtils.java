import java.util.Scanner;

public class TestUtils {
    public static String stringFromFileWithoutSpaces(Scanner scanner){
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext()){
            result.append(scanner.nextLine());
        }
        return result.toString().replace(" ", "").replace("\\n", "");
    }
}
