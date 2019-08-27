public class ListCommand extends Command {
    /**
     * Constructor
     */
    public ListCommand() {
        super(null);
    }

    @Override
    public void execute(TaskList tasklist) {
        tasklist.print();
    }
}
