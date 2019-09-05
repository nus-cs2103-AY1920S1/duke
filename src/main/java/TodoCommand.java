import java.io.IOException;

/**
 * This is a class for command dealing with additions of new todo tasks.
 * @author Choong Yong Xin
 */

public class TodoCommand extends Command {

    TodoCommand(String commandDesc) {
        super(commandDesc);
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return false as command is not an exit command.
     */
    boolean isExit() {
        return false;
    }

    /**
     * Returns a string response by Quack when the command is executed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed
     */
    @Override
    String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        try {
            //Check if description is empty.
            if (!commandDesc.substring(5).equals((""))) {
                Todo newTodo = new Todo(commandDesc.substring(5));
                storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newTodo.stringForAppend());
                tasks.addToDo(newTodo);
                String output = "Got it. I've added this task: \n";
                output += (newTodo + "\n");
                output += "Now you have " + tasks.taskList.size() + " tasks in the list.\n";
                return output;
            } else {
                throw new EmptyDescDukeException("todo");
            }
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("todo");
        }
    }
}
