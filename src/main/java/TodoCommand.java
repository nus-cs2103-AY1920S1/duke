/**
 * This is a class for command dealing with additions of new todo tasks.
 * @author Choong Yong Xin
 */


import java.io.IOException;

public class TodoCommand extends Command {

    TodoCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit(){
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            /*Check if description is empty (does not check when user input
              multiple spaces as the description.
            */
            if (!commandDesc.substring(5).equals((""))) {
                Todo newTodo = new Todo(commandDesc.substring(5));
                storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newTodo.stringForAppend());
                tasks.addToDo(newTodo);
            } else {
                throw new EmptyDescDukeException("todo");
            }
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("todo");
        }
    }
}
