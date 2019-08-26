public class ListCommand extends Command {
    public ListCommand() {
        super(null);
    }

    @Override
    public void execute(TaskList tasklist) {
        tasklist.print();
    }
}
