import main.java.com.github.fileparser.Parser;
import main.java.com.github.fileparser.Provider;
import main.java.com.github.fileparser.data.ParseCommentResult;
import main.java.com.github.fileparser.utils.RegexProvider;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        
        System.out.println("Enter the file path: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        String extension = null;
        int i = path.lastIndexOf('.');
        if (i == 0) {
            System.out.println("Not supported file type");
        }
        if (i > 0) {
            extension = path.substring(i+1);
        }
        System.out.println("Your file path is " + path);
        System.out.println("Processing...");

        List<String> file = Provider.provider(new File(path));
        //System.out.println("Hello World!");
        ParseCommentResult result = Parser.parse(file, RegexProvider.getRegex(extension));
        System.out.println(result.toString());





    }
}
