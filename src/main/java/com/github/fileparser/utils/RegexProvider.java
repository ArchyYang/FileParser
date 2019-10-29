package main.java.com.github.fileparser.utils;

import main.java.com.github.fileparser.data.RegexComment;

public class RegexProvider {
    static final String JAVA = "java";
    static final String CPP = "cpp";
    static final String C = "c";
    static final String JAVASCRIPT = "js";
    public static final String REGEX_BLOCK_COMMENT_START = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\/\\*.*)";
    public static final String REGEX_BLOCK_COMMENT_END = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)";
    public static final String REGEX_SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(//.*)";

    static final String PHP = "php";
    public static final String PHP_REGEX_SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(//|#.*)";
    static final String HTML = "html";


    public static RegexComment getRegex(String extension) {
        if (extension == null) {
            return null;
        }
        if (extension.equals(JAVA) || extension.equals(CPP) || extension.equals(C)
            || extension.equals(JAVASCRIPT)) {
            return new RegexComment(REGEX_SINGLE_LINE_COMMENT,
                                    REGEX_BLOCK_COMMENT_START,
                                    REGEX_BLOCK_COMMENT_END);
        }
        else if (extension == PHP) {
            return new RegexComment(PHP_REGEX_SINGLE_LINE_COMMENT,
                                    REGEX_BLOCK_COMMENT_START,
                                    REGEX_BLOCK_COMMENT_END);
        }

        return null;
    }
}
