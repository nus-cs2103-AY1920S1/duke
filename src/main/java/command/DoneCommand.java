package command;

import parser.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represent a task has been done command
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * @param index index of task that is done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Sets task identified by its index as done
     * Prints messages to notify users task is marked as done
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.index < 0 || this.index >= tasks.getTasks().size()) {
            Command incorrectCommand = new IncorrectCommand();
            incorrectCommand.execute(tasks, ui, storage);
        } else {
            tasks.getTasks().get(this.index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(
                    "[" + tasks.getTasks().get(this.index).getDoneIcon()
                    + "]" + tasks.getTasks().get(this.index).getDescription());
        }
    }
}
