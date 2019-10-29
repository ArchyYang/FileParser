package test.java.com.github.flieparser;

import main.java.com.github.fileparser.Parser;
import main.java.com.github.fileparser.data.RegexComment;
import main.java.com.github.fileparser.utils.RegexProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    RegexComment regexComment = RegexProvider.getRegex("java");

    @Test
    public void endCommentShouldBeFound() {
        String str1 = "some comment*/";
        String str2 = "some comment */ int a = 5;";
        Assertions.assertEquals(str1, Parser.findBlockCommentEnd(str1, regexComment.getBlockCommentEnd()));
        Assertions.assertEquals("some comment */", Parser.findBlockCommentEnd(str2, regexComment.getBlockCommentEnd()));
    }


    @Test
    public void startCommentShouldBeFound() {
        String str1 = "/* some comments";
        String str2 = "int a = 2; /* some comments";
        String str3 = "int a = 2; /* some comments*/";
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str1, regexComment.getBlockCommentStart()));
        Assertions.assertEquals(str1, Parser.findBlockCommentStart(str2, regexComment.getBlockCommentStart()));
        Assertions.assertEquals("/* some comments*/", Parser.findBlockCommentStart(str3,regexComment.getBlockCommentStart()));
    }

    @Test
    public void lineCommentShouldBeFound() {
        String str1 = "//some comments";
        String str2 = "int a = 2; //some comments";
        Assertions.assertEquals(str1, Parser.findLineComment(str1, regexComment.getSingleLineComment()));
        Assertions.assertEquals(str1, Parser.findLineComment(str2, regexComment.getSingleLineComment()));
    }
}
