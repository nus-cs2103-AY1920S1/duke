package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.exceptions.DukeException;
import duke.tasks.Todo;
import duke.tasks.Task;

public class TodoCommand extends Command {

    private String inputInstruction;

    public TodoCommand(String inputString) {
        inputInstruction = inputString;
    }

    /**
     * Overrides execute method to achieve TodoCommand.
     * @param currentTaskList taskList containing all the current Task
     * @param ui class that handles all I/O managements
     * @param storage class that handles all file storage and loading
     * @return a String output for the specific Commands
     */
    public String execute(TaskList currentTaskList, Ui ui, Storage storage) {
        try {
            if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                throw new DukeException("todo");
            }
            String subInput = inputInstruction.substring(5);
            Task newTask = new Todo(subInput);
            currentTaskList.addTask(newTask);
            storage.writeToFile(newTask + "\n");
            return ui.printTodo(newTask, currentTaskList);
        } catch (Exception e) {
            return ui.printException(e);
        }
    }
}
