package command;

import exception.ToDoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Command for creating a ToDo Task.
 */
public class ToDoCommand extends Command {

    protected String[] input;

    public ToDoCommand(String[] input) {
        this.input = input;
    }

    /**
     * Execute creating ToDo task.
     * Output what is needed.
     * @param tasks the TaskList.
     * @param ui the User Interface which responsible for every output printing.
     * @param storage user's hard disk storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ToDoException {
        if (input.length <= 1) {
            throw new ToDoException();
        } else {
            String description = input[1];
            for (int i = 2; i < input.length; i++) {
                description += " " + input[i];
            }
            Task newToDoTask = new ToDo(description);
            tasks.getTaskList().add(newToDoTask);
            ui.println("Got it. I've added this task:");
            ui.println("  " + newToDoTask);
            if (tasks.getTaskList().size() > 1) {
                ui.println("Now you have " + tasks.getTaskList().size() + " tasks in the list.");
            } else {
                ui.println("Now you have " + tasks.getTaskList().size() + " task in the list.");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
