public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(String command, int index) {
        super(command);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public void execute() {

    }
}
