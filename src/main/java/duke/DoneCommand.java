package duke;

public class DoneCommand extends Command {
    private Integer index;

    public DoneCommand(Integer index) {
        super(CommandType.DONE);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }
}
