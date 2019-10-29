package main.java.com.github.fileparser.utils;

public class CommentToken {

    public static final String REGEX_BLOCK_COMMENT_START = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\/\\*.*)";
    public static final String REGEX_BLOCK_COMMENT_END = "(([^\"//]*\"[^\"//]*\")*[^\"]*)(\\*\\/)(.*)";

    //java
    public static final String REGEX__SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(//.*)";

    //python
    public static final String REGEX__PYTHON_SINGLE_LINE_COMMENT = "(([^\"]*\"[^\"]*\")*[^\"]*)(#.*)";

}
