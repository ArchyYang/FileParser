package main.java.com.github.fileparser.data;

import java.util.ArrayList;
import java.util.List;

/**
 * the parsed result
 */
public class ParseCommentResult {
    private List<LineComment> lineComments;
    private List<BlockComment> blockComments;
    private int totalLineNum;
    private int totalTODONum;

    public ParseCommentResult() {
        totalLineNum = 0;
        totalTODONum = 0;
    }

    /**
     * add a line comment into this object.
     * @param lineComment
     */
    public void addLineComment(LineComment lineComment) {
        if (lineComments == null) {
            this.lineComments = new ArrayList<>();
        }
        lineComments.add(lineComment);
        totalLineNum++;
        totalTODONum += lineComment.hasTODO() ? 1 : 0;
    }

    /**
     * add a block comment into this object.
     * @param blockComment
     */

    public void addBlockComment(BlockComment blockComment) {
        if (blockComments == null) {
            this.blockComments = new ArrayList<>();
        }
        blockComments.add(blockComment);
        totalLineNum += blockComment.getNumOfLines();
        totalTODONum += blockComment.getNumTODO();
    }

    public int getNumberOfLineComments() {
        return lineComments.size();
    }

    public int getNumberOfBlockComments() {
        return blockComments.size();
    }

    public List<LineComment> getLineComments() {
        return lineComments;
    }

    public List<BlockComment> getBlockComments() {
        return blockComments;
    }

    public int getTotalLineNum() {
        return totalLineNum;
    }

    public void setTotalLineNum(int totalLineNum) {
        this.totalLineNum = totalLineNum;
    }

    public String toString() {
        String result = "======== All line comments: ========" + System.lineSeparator();
        for (LineComment lineComment: lineComments) {
            result += lineComment.toString();
        }

        result += "======== All block comments: ========" + System.lineSeparator();
        for (BlockComment blockComment: blockComments) {
            result += blockComment.toString();
        }

        result += "======== Total number of lines: " + totalLineNum + " ========" + System.lineSeparator();
        result += "======== Total number of TODOs: " + totalTODONum + " ========" + System.lineSeparator();

        return result;
    }
}
