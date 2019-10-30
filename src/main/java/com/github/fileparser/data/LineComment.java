package main.java.com.github.fileparser.data;

public class LineComment {

    private boolean hasTODO = false;
    private final String TODO = "TODO";
    private String comment;
    private int lineNumber;


    public LineComment(String comment, int lineNumber) {
        setComment(comment);
        if (comment.contains(TODO)) {
            hasTODO = true;
        };
        this.lineNumber = lineNumber;
    }

    public boolean hasTODO() {
        return hasTODO;
    }

    public int getStartLineNumber() {
        return lineNumber;
    }

    public void setStartLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return lineNumber + ":    " + comment + System.lineSeparator();
    }
}
