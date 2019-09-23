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

    private String errorMessage = "";
    private Todo newTodo;
    private String[] inputMessage;
    private TaskList taskList;

    /**
     * Constructor that takes in the main message of the todo.
     * @param message The main message of the todo.
     */
    public AddTodoCommand(String message) {
        super(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.taskList = listOfTasks;
        try {
            assert !getMessage().isEmpty();
        } catch (AssertionError error) {
            errorMessage = "Description of a todo cannot be empty!";
        }
        if (getMessage().isEmpty()) {
            errorMessage = "Description of a todo cannot be empty!";
            return;
        }
        newTodo = new Todo(getMessage());
        listOfTasks.addTodo(newTodo);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("")) {
            return errorMessage;
        } else {
            String output = "Done! I have added this task to the list:\n\n" + newTodo.toString() + "\n\n";
            output += "You now have " + taskList.size() + " tasks in the list";
            return output;
        }
    }
}
