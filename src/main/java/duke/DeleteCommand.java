package duke;

public class DeleteCommand extends Command {
    private Integer index;

    public DeleteCommand(Integer index) {
        super(CommandType.DELETE);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
