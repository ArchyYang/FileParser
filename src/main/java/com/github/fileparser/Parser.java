package main.java.com.github.fileparser;

import main.java.com.github.fileparser.data.BlockComment;
import main.java.com.github.fileparser.data.LineComment;
import main.java.com.github.fileparser.data.ParseCommentResult;
import main.java.com.github.fileparser.data.RegexComment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {

    public static ParseCommentResult parse(List<String> file, RegexComment regexComment) {

        ParseCommentResult result = new ParseCommentResult();
        BlockComment blockComment = new BlockComment();
        int lineNumber = 0;
        for (String line: file) {
            lineNumber++;
            String comment;
            // Check if the line is inside block comment.
            if (!blockComment.isEmpty()) {
                comment = findBlockCommentEnd(line, regexComment.getBlockCommentEnd());
                if (comment!=null) {
                    blockComment.addComment(new LineComment(comment,lineNumber));
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                } else {
                    blockComment.addComment(new LineComment(line,lineNumber));
                };
                continue;
            }

            // Check if the line contains /* ...
            comment = findBlockCommentStart(line, regexComment.getBlockCommentStart());
            if (comment != null) {
                blockComment = new BlockComment();
                blockComment.addComment(new LineComment(comment,lineNumber));
                if (comment.contains("*/")){
                    // inside content
                    result.addBlockComment(blockComment);
                    blockComment = new BlockComment();
                }
                continue;
            }

            // Check if the line has //
            comment = findLineComment(line, regexComment.getSingleLineComment());
            if (comment != null) {
                result.addLineComment(new LineComment(comment, lineNumber));
            }
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

    public static String findBlockCommentEnd(String line, String regex) {

        //Pattern endCommentWithTrailingCodePattern = Pattern.compile("(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)");
        Pattern endCommentWithTrailingCodePattern = Pattern.compile(regex);
        Matcher matcher = endCommentWithTrailingCodePattern.matcher(line);
        if (matcher.matches()) {
            // end of block comment
            String comment = matcher.group(1) + matcher.group(3);
            // reach */ block comment end.
            if (matcher.groupCount() >= 5 &&  matcher.group(4) != null) {
                //TODO: code
            }
            return comment;
        }
        return null;
    }

    /**
     *
     * @param line
     * @return
     */
    public static String findBlockCommentStart(String line, String regex) {

        //Pattern blockCommentStartPattern = Pattern.compile("(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\/\\*.*)");
        Pattern blockCommentStartPattern = Pattern.compile(regex);
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

    public static String findLineComment(String line, String regex) {
        //Pattern lineCommentPattern = Pattern.compile("(([^\"]*\"[^\"]*\")*[^\"]*)(//.*)");
        Pattern lineCommentPattern = Pattern.compile(regex);
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
