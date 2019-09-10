package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;
import duke.ui.UI;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * Class that represent command to add a new todo.
 */
public class AddTodoCommand extends Command {

    private Todo newTodo;
    private String[] inputMessage;
    /**
     * Constructor that takes in the main message of the todo.
     * @param message The main message of the todo.
     */
    public AddTodoCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        inputMessage = input.split(" ");
        if (inputMessage.length == 0) {
            throw new DukeException("     OOPS!! The description of a todo cannot be empty");
        }
        String item = "";
        for (int i = 0; i < inputMessage.length; i++) {
            if (i == inputMessage.length - 1) {
                item += inputMessage[i];
            } else {
                item += inputMessage[i];
                item += " ";
            }
        }
        newTodo = new Todo(item);
        listOfTasks.addTodo(newTodo);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    public String toString() {
        if (inputMessage.length == 0) {
            return "OOPS!! The description of a todo cannot be empty";
        } else {
            return newTodo.toString();
        }
    }
}
