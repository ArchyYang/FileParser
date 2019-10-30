import main.java.com.github.fileparser.Parser;
import main.java.com.github.fileparser.Provider;
import main.java.com.github.fileparser.data.ParseCommentResult;
import main.java.com.github.fileparser.data.RegexComment;
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
            System.out.println("Invalid input file");
        }
        if (i > 0) {
            extension = path.substring(i+1);
        }
        System.out.println("The file path is " + path);

        List<String> file = Provider.provider(new File(path));
        RegexComment regex = RegexProvider.getRegex(extension);
        if (regex == null) {
            System.out.println("The file type ." + extension + " is not supported currently ");
            return;
        }
        ParseCommentResult result = Parser.parse(file, regex);
        System.out.println(result.toString());

    }
}
