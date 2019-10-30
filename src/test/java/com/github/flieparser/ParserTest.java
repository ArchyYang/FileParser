package test.java.com.github.flieparser;

import main.java.com.github.fileparser.Parser;
import main.java.com.github.fileparser.data.RegexComment;
import main.java.com.github.fileparser.utils.RegexProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    RegexComment regexComment = RegexProvider.getRegex("java");

    @Test
    public void javaEndCommentShouldBeFound() {
        String str1 = "some comment*/";
        String str2 = "some comment */ int a = 5;";
        Assertions.assertEquals(str1, Parser.findBlockCommentEnd(str1, regexComment.getBlockCommentEnd()));
        Assertions.assertEquals("some comment */", Parser.findBlockCommentEnd(str2, regexComment.getBlockCommentEnd()));
    }


    @Test
    public void javaStartCommentShouldBeFound() {
        String str1 = "/* some comments";
        String str2 = "int a = 2; /* some comments";
        String str3 = "int a = 2; /* some comments*/";
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str1, regexComment.getBlockCommentStart()));
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str2, regexComment.getBlockCommentStart()));
        Assertions.assertEquals("/* some comments*/", Parser.findBlockCommentStart(str3,regexComment.getBlockCommentStart()));
    }

    @Test
    public void javaLineCommentShouldBeFound() {
        String str1 = "//some comments";
        String str2 = "int a = 2; //some comments";
        Assertions.assertEquals(str1, Parser.findLineComment(str1, regexComment.getSingleLineComment()));
        Assertions.assertEquals(str1, Parser.findLineComment(str2, regexComment.getSingleLineComment()));
    }

    RegexComment pythonRegexComment = RegexProvider.getRegex("py");

    @Test
    public void pythonEndCommentShouldBeFound() {
        String str1 = "some comment\"\"\"";
        String str2 = "some comment\"\"\"a = 5;";
        Assertions.assertEquals(str1, Parser.findBlockCommentEnd(str1, pythonRegexComment.getBlockCommentEnd()));
        Assertions.assertEquals(str1, Parser.findBlockCommentEnd(str2, pythonRegexComment.getBlockCommentEnd()));
    }


    @Test
    public void pythonStartCommentShouldBeFound() {
        String str1 = "\"\"\"some comments";
        String str2 = "int a = 2;\"\"\"some comments";
        String str3 = "int a = 2;\"\"\"some comments\"\"\"";
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str1, pythonRegexComment.getBlockCommentStart()));
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str2, pythonRegexComment.getBlockCommentStart()));
        Assertions.assertEquals(str1+"\"\"\"", Parser.findBlockCommentStart(str3,pythonRegexComment.getBlockCommentStart()));
    }

    @Test
    public void pythonLineCommentShouldBeFound() {
        String str1 = "#some comments";
        String str2 = "int a = 2; #some comments";
        Assertions.assertEquals(str1, Parser.findLineComment(str1, pythonRegexComment.getSingleLineComment()));
        Assertions.assertEquals(str1, Parser.findLineComment(str2, pythonRegexComment.getSingleLineComment()));
    }
}
