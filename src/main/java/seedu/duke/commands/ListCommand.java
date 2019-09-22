package seedu.duke.commands;

import seedu.duke.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super(null, taskList);
    }

    @Override
    public String execute() {
        return list();
    }

    /**
     * Lists all the tasks.
     * @return The list.
     */
    private String list() {
        return "Here are the tasks in your list:\n"
                + taskList.toString() + "\n";
    }
}
