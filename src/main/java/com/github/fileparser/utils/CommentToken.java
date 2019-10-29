package main.java.com.github.fileparser.utils;

public class CommentToken {

    public static final String REGEX_EVEN_TIMES_DOUBLE_QUOTE = "([^\"]*\"[^\"]*\")*[^\"]*";
    //public static final String REGEX_EVEN_TIMES_DOUBLE_QUOTE_WITHOUT_DOUBLE_SLASH = "([^\"]*\"[^\"]*\")*[^\"////]*";
    public static final String REGEX_EVEN_TIMES_DOUBLE_QUOTE_WITHOUT_DOUBLE_SLASH = "([^\"//]*\"[^\"//]*\")*[^\"//]*";
    //public static final String REGEX_BLOCK_COMMENT_START = "(^/\\*)";
    //public static final String REGEX_BLOCK_COMMENT_END = "(.*?\\*/)";
    //public static final String REGEX_BLOCK_COMMENT = "(^/\\*.*?\\*/)";


    //[^"]*"([^"]*"[^"]*")*[^"]*
    public static final String REGEX_BLOCK_COMMENT_END_WITH_TRAIL_CODE = REGEX_EVEN_TIMES_DOUBLE_QUOTE + "(\\*\\/)(.*)";
    public static final String REGEX_BLOCK_COMMENT_END_WITHOUT_TRAIL_CODE = REGEX_EVEN_TIMES_DOUBLE_QUOTE + "\\*/";

    public static final String REGEX_TRAILING_BLOCK_COMMENT_START = REGEX_EVEN_TIMES_DOUBLE_QUOTE_WITHOUT_DOUBLE_SLASH + "(\\\\\\*)";
    public static final String REGEX_BLOCK_COMMENT_START = "\\/.*";

    //public static final String REGEX_TRAILING_BLOCK_COMMENT = REGEX_EVEN_TIMES_DOUBLE_QUOTE + "(^/\\*.*?\\*/)";
    //public static final String REGEX_BLOCK_COMMENT = "(^/\\*.*?\\*/)";
    //java
    public static final String REGEX_LINE_COMMENT = "(//.*?$)";
    public static final String REGEX_TRAILING_LINE_COMMENT = REGEX_EVEN_TIMES_DOUBLE_QUOTE + "(//.*$)";

    //python
    public static final String PYTHON_REGEX_LINE_COMMENT = "(#.*?$)";

}
