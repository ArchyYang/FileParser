package main.java.com.github.fileparser;

import main.java.com.github.fileparser.data.BlockComment;
import main.java.com.github.fileparser.data.LineComment;
import main.java.com.github.fileparser.data.ParseCommentResult;
import main.java.com.github.fileparser.data.RegexComment;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    /**
     * Given regex and file, parse out data.
     * @param file
     * @param regexComment
     * @return
     */
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
            //TODO: line is source code.
        }
        result.setTotalLineNum(lineNumber);
        return result;
    }

    /**
     * findBlockCommentEnd
     * @param line
     * @param regex
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
                // TODO: matcher.group(4) is a line of source code.
            }
            return comment;
        }
        return null;
    }

    /**
     * findBlockCommentStart
     * @param line
     * @param regex
     * @return
     */
    public static String findBlockCommentStart(String line, String regex) {

        Pattern blockCommentStartPattern = Pattern.compile(regex);
        Matcher matcher = blockCommentStartPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (comment.equals(matcher.group(0))) {
                // TODO: matcher.group(0) is a line of source code
            }
            return comment;
        }
        return null;
    }

    /**
     * findLineComment
     * @param line
     * @param regex
     * @return
     */
    public static String findLineComment(String line, String regex) {
        Pattern lineCommentPattern = Pattern.compile(regex);
        Matcher matcher = lineCommentPattern.matcher(line);
        if (matcher.matches()) {
            String comment = matcher.group(3);
            if (matcher.group(1) != null) {
                // TODO: matcher.group(1) is a line of source code
            }
            return comment;
        }
        return null;
    }

}
