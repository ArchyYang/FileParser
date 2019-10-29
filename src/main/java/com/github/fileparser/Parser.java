package main.java.com.github.fileparser;

import main.java.com.github.fileparser.data.BlockComment;
import main.java.com.github.fileparser.data.LineComment;
import main.java.com.github.fileparser.data.ParseCommentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    /*
     *
     */

    static String moreFun = " /* comment? doubt that */ String x = t;";
    static List<Match> commentMatches = new ArrayList<Match>();

    String evenMoreFun = " // comment? doubt that ";

    static class Match {
        int start;
        String text;
    }

    public static ParseCommentResult parse(List<String> file) {
        //Pattern commentsPattern = Pattern.compile("(//.*?$)|(/\\*.*?\\*/)|(\\*.*?$)");
        //Pattern stringsPattern = Pattern.compile("(\".*?(?<!\\\\)\")");
/*
        Pattern endCommentPattern = Pattern.compile(CommentToken.REGEX_BLOCK_COMMENT_END_WITHOUT_TRAIL_CODE);
        Pattern endCommentWithTrailingCodePattern = Pattern.compile(CommentToken.REGEX_BLOCK_COMMENT_END_WITH_TRAIL_CODE);
        Pattern lineCommentPattern = Pattern.compile(CommentToken.REGEX_LINE_COMMENT);
        Pattern lineTrailingCommentPattern = Pattern.compile(CommentToken.REGEX_LINE_COMMENT);
        Pattern blockCommentStartPattern = Pattern.compile(CommentToken.REGEX_TRAILING_BLOCK_COMMENT_START);
*/
        ParseCommentResult result = new ParseCommentResult();
        BlockComment blockComment = new BlockComment();
        int lineNumber = 0;
        for (String line: file) {
            lineNumber++;
            String comment;
            // Check if the line is inside block comment.
            if (!blockComment.isEmpty()) {
                comment = findBlockCommentEnd(line);
                if (comment!=null) {
                    blockComment.addComment(new LineComment(comment,lineNumber));
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                } else {
                    blockComment.addComment(new LineComment(line,lineNumber));
                }
                //lineNumber++;
                continue;
            }

            // Check if the line contains /* ...
            comment = findBlockCommentStart(line);
            if (comment != null) {
                blockComment = new BlockComment();
                blockComment.addComment(new LineComment(comment,lineNumber));
                if (comment.contains("*/")){
                    // inside content
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                }
                //lineNumber++;
                continue;
            }



            // Check if the line has // or CODE //
            comment = findLineComment(line);
            if (comment != null) {
                result.addLineComment(new LineComment(comment, lineNumber));
            }
            //ineNumber++;
            //TODO: code


        }
        result.setTotalLineNum(lineNumber);
        return result;
    }

    /**
     *
     * @param line
     * @return
     */

    public static String findBlockCommentEnd(String line) {

        Pattern endCommentWithTrailingCodePattern = Pattern.compile("(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)");
        Matcher matcher = endCommentWithTrailingCodePattern.matcher(line);
        if (matcher.matches()) {
            // end of block comment
            String comment = matcher.group(1) + matcher.group(3);
            // reach */ block comment end.
            if (matcher.groupCount() >= 5 &&  matcher.group(4) != null) {
                //code line ++
            }
            return comment;
        }

        // Block Comment Body
        return null;

    }

    /**
     *
     * @param line
     * @return
     */
    public static String findBlockCommentStart(String line) {

        Pattern blockCommentStartPattern = Pattern.compile("(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\/\\*.*)");
        Matcher matcher = blockCommentStartPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (comment.equals(matcher.group(0))) {
                // code ++
            }
            return comment;
        }
        return null;
    }

    public static String findLineComment(String line) {
        Pattern lineCommentPattern = Pattern.compile("(([^\"]*\"[^\"]*\")*[^\"]*)(//.*)");
        Matcher matcher = lineCommentPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (matcher.group(1) != null) {
                // code ++
            }
            return comment;
        }
        return null;
    }

}
