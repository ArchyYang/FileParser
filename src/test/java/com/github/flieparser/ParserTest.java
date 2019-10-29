package test.java.com.github.flieparser;

import main.java.com.github.fileparser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void endCommentShouldBeFound() {
        String str1 = "some comment*/";
        String str2 = "some comment */ int a = 5;";
        Parser.findBlockCommentEnd(str2);
        Assertions.assertEquals(str1, Parser.findBlockCommentEnd(str1));
        Assertions.assertEquals("some comment */", Parser.findBlockCommentEnd(str2));
    }


    @Test
    public void startCommentShouldBeFound() {
        String str1 = "/* some comments";
        String str2 = "int a = 2; /* some comments";
        String str3 = "int a = 2; /* some comments*/";
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str2));
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str2));
        Assertions.assertEquals("/* some comments*/", Parser.findBlockCommentStart(str3));
    }

    @Test
    public void lineCommentShouldBeFound() {
        String str1 = "//some comments";
        String str2 = "int a = 2; //some comments";
        Assertions.assertEquals(str1, Parser.findLineComment(str1));
        Assertions.assertEquals(str1, Parser.findLineComment(str2));
    }
}
