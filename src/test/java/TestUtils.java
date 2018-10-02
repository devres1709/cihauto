import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestUtils {
    public static String stringFromFileWithoutSpaces(Scanner scanner){
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext()){
            result.append(scanner.nextLine());
        }
        return result.toString().replaceAll("\\s+","").replace("\\n", "");
    }

    public static boolean compareResult(String pathToFileResultOfWork, String pathToStandert) throws FileNotFoundException {
        String resultOfWork = TestUtils.stringFromFileWithoutSpaces(new Scanner(new File(pathToFileResultOfWork)));
        String standart = TestUtils.stringFromFileWithoutSpaces(new Scanner(new File(pathToStandert)));
        System.out.println(standart + "\n" + resultOfWork);
        return resultOfWork.equals(standart);
    }
}
