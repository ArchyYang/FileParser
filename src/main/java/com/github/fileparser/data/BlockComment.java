package main.java.com.github.fileparser.data;

import java.util.ArrayList;
import java.util.List;

public class BlockComment {
    private List<LineComment> blockComment;
    private int numTODO;

    public BlockComment() {
        this.numTODO = 0;
        blockComment = new ArrayList<>();
    }

    public void addComment(LineComment lineComment) {
        blockComment.add(lineComment);
        numTODO += lineComment.hasTODO() ? 1 : 0;
    }

    public int getNumOfLines() {
        return blockComment.size();
    }

    public boolean isEmpty() {
        return blockComment.size() == 0;
    }

    public String toString() {
        String comment = "";
        for (LineComment line: blockComment) {
            comment += line.toString();
        }
        return comment;
    }

    public int getNumTODO() {
        return numTODO;
    }
}
