package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UserInterface;

/**
 * The <code>AddCommand</code> is the parent class of {@link AddTodoCommand}, {@link AddDeadlineCommand},
 * {@link AddEventCommand}.
 */
public class AddCommand implements Command {

    /**
     * This is the task to be added by the add command.
     */
    protected Task task;

    /**
     * This is the line contents of the command passed as a parameter.
     */
    protected String line;

    /**
     * Constructs a new add command that will be executed in the <code>run</code> method of {@link duke.main.Duke}
     * with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     */
    public AddCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the command. This will add the specified task into the list of tasks. The task can be a todo, deadline
     * or event item.
     * @param tasks the list of tasks
     * @param commandLineUserInterface the user interface
     * @param storage the storage for the tasks
     */
    public void execute(TaskList tasks, UserInterface commandLineUserInterface, Storage storage) {
        tasks.add(task);
        commandLineUserInterface.showAddInformation(task.toString(), tasks.size());
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }

}
