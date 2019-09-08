package duke.task;

public class UndoInfo {
    private String undoCommand;
    private int indexToAdd;

    public UndoInfo(String undoCommand) {
        this.indexToAdd = -1;
        this.undoCommand = undoCommand;
    }

    public UndoInfo(String undoCommand, int indexToAdd) {
        this.indexToAdd = indexToAdd;
        this.undoCommand = undoCommand;
    }

    public String getUndoCommand() {
        return undoCommand;
    }

    public int getIndexToAdd() {
        return indexToAdd;
    }
}
