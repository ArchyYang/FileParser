import main.java.com.github.fileparser.Parser;
import main.java.com.github.fileparser.Provider;
import main.java.com.github.fileparser.data.ParseCommentResult;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        
        System.out.println("Enter the file path: ");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Your file path is " + path);
        System.out.println("Processing...");

        List<String> file = Provider.provider(new File(path));
        //System.out.println("Hello World!");
        ParseCommentResult result = Parser.parse(file);
        System.out.println(result.toString());





    }
}
