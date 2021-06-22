package command;

import exception.ToDoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.UndoStack;
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
     * @return Executed output as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ToDoException {
        if (input.length <= 1) {
            throw new ToDoException();
        } else {
            String description = input[1];
            for (int i = 2; i < input.length; i++) {
                description += " " + input[i];
            }
            Task newToDoTask = new ToDo(description);
            UndoStack.add(tasks); //Add to previous task to UndoStack
            tasks.addTask(newToDoTask);
            String output = "";
            output += "Got it. I've added this task:\n";
            output += "  " + newToDoTask + "\n";
            output += taskListInformation(tasks);

            assert !output.equals("") : "Output should not be empty";

            return output;
        }
    }
}
