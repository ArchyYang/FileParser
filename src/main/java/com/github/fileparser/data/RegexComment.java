package main.java.com.github.fileparser.data;

public class RegexComment {
    private String singleLineComment;
    private String blockCommentStart;
    private String blockCommentEnd;

    public RegexComment(String singleLineComment, String blockCommentStart, String blockCommentEnd) {
        this.singleLineComment = singleLineComment;
        this.blockCommentStart = blockCommentStart;
        this.blockCommentEnd = blockCommentEnd;
    }

    public String getSingleLineComment() {
        return singleLineComment;
    }

    public void setSingleLineComment(String singleLineComment) {
        this.singleLineComment = singleLineComment;
    }

    public String getBlockCommentStart() {
        return blockCommentStart;
    }

    public void setBlockCommentStart(String blockCommentStart) {
        this.blockCommentStart = blockCommentStart;
    }

    public String getBlockCommentEnd() {
        return blockCommentEnd;
    }

    public void setBlockCommentEnd(String blockCommentEnd) {
        this.blockCommentEnd = blockCommentEnd;
    }
}
