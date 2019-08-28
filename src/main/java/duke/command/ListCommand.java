package duke.command;


import duke.task.TaskList;

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
