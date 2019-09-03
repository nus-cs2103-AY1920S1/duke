package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;

/**
 * Abstract class that consolidates common operations for Commands that add
 * tasks, such as EventCommand and TodoCommand.
 */
public abstract class AddCommand extends Command {

    /**
     * Constructs an AddCommand with the given details.
     *
     * @param details   Details of task to be added
     */
    public AddCommand(String details) {
        super(details);
    }

    /**
     * Displays a message indicating the successful addition of a new Task
     * and attempts to save the task list to storage.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If an error occurs when saving the new task list
     * @return                  String containing Duke's response
     */
    @Override
    public String execute(TaskList tasks, TextUi ui, Storage storage) {
        int numberOfTasks = tasks.size();
        String textToDisplay = "Got it. I've added this task:"
                + "\n  " + tasks.get(numberOfTasks - 1)
                + "\nNow you have " + numberOfTasks + " tasks in the list.\n";
        ui.showText(textToDisplay);
        try {
            save(tasks, storage);
        } catch (DukeException e) {
            System.err.print(e.getMessage());
        }
        return textToDisplay;
    }
}
