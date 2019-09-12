package command;

import exception.DoneException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Command for specifying that a task is done.
 */
public class DoneCommand extends Command {

    protected String[] input;

    public DoneCommand(String[] input) {
        this.input = input;
    }

    /**
     * Execute 'ticking' a task in user's tasks list.
     * Output what is needed.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DoneException {
        if (input.length <= 1) {
            throw new DoneException();
        } else {
            int taskToBeDone = Integer.parseInt(input[1]);
            if (tasks.getSize() < taskToBeDone) {
                throw new DoneException("OOPS!!! There is no such task in your list!\n"
                        + "Current number of tasks = " + tasks.getTaskList().size());
            } else {
                String output = "";
                Task task = tasks.getTask(taskToBeDone - 1);
                if (task.isDone()) {
                    output += "Hey! I've already marked this task as done :)\n";
                    output += "  " + task + "\n";
                } else {
                    task.markAsDone();
                    output += "Nice! I've marked this task as done:\n";
                    output += "  " + task + "\n";
                }

                assert !output.equals("") : "Output should not be empty";

                return output;
            }
        }
    }
}
