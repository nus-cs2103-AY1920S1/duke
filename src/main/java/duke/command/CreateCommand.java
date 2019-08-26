package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

public class CreateCommand extends Command {

    public static Task createTodo(String details) throws DukeException {
        Task todo = null;
        try {
            String params = details.trim();
            if (params.isEmpty()) {
                throw new IllegalArgumentException();
            }

            todo = new Todo(params);
            return todo;

        } catch (IllegalArgumentException e) {
            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("To enter a new todo, type: todo <description>");
        } // End catch.
    } // End method.

    public static Task createDeadline(String details) throws DukeException {
        Task deadline = null;
        try {
            String params = details.trim();
            String[] paramsArr = params.split("/by");

            if (params.isEmpty() || paramsArr[0].isEmpty() || paramsArr[1].isEmpty()) {
                throw new IllegalArgumentException();
            }

            deadline = new Deadline(paramsArr[0].trim(), paramsArr[1].trim());
            return deadline;

        } catch (IllegalArgumentException e) {
            throw new DukeException(":( OOPS!!! The description or due date of a deadline cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The description or due date of a deadline cannot be empty! "
                    + "To enter a new deadline, type: deadline <description> /by <date>");
        } // End catch.
    } // End method.

    public static Task createEvent(String details) throws DukeException {
        Task event = null;
        try {
            String params = details.trim();
            String[] paramsArr = params.split("/at");

            if (params.isEmpty() || paramsArr[0].isEmpty() || paramsArr[1].isEmpty()) {
                throw new IllegalArgumentException();
            }

            event = new Event(paramsArr[0].trim(), paramsArr[1].trim());
            return event;

        } catch (IllegalArgumentException e) {
            throw new DukeException(":( OOPS!!! The description or date and time of an event cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You need to enter an event? "
                    + "To enter a new event, type: event <description> /at <date&time>");
        } // End catch.
    } // End method.

    @Override
    public void execute(Tasklist list, Ui ui) throws DukeException {
        return;
    } // End method.
}
