package duke.command;

import duke.exception.InvalidParameterException;
import duke.task.Todo;

/**
 * The <code>AddTodoCommand</code> is created when the user enters <code>"todo"</code>. The todo command add a todo task
 * into the list of tasks in {@link duke.task.TaskList}. The user interface will display the new todo task that is added
 * .
 */
public class AddTodoCommand extends AddCommand {

    /**
     * Constructs a new add todo command that will be executed in the <code>run</code> method of {@link duke.main.Duke}
     * with the specified line as a parameter.
     * @param line the line contents of the command passed as a parameter
     * @throws InvalidParameterException if the line is blank
     */
    public AddTodoCommand(String line) {
        super(line);
        if(line.isBlank()) {
            throw new InvalidParameterException();
        } else {
            super.task = new Todo(line);
        }
    }

    /**
     * Returns <code>true</code> if the command is an exit command and <code>false</code> otherwise.
     * @return <code>false</code>
     */
    public boolean isExit() {
        return false;
    }

}
