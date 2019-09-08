package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.ArgumentNotNumberException;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TaskDoesNotExistException;
import seedu.duke.exceptions.TaskNotSpecifiedException;
import seedu.duke.task.Task;

public class DoneCommand extends Command {
    public DoneCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return done(arg);
    }

    /**
     * Marks a task as done.
     * @param s The index of the task to be marked as done, represented as a String
     * @return The response.
     * @throws DukeException If {@code s} cannot be parsed to an Integer, or if the integer as an index is not
     *         within the {@code TaskList} bound.
     */
    private String done(String s) throws DukeException {
        assert s != null;
        try {
            if (s.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            int i = Integer.parseInt(s);
            Task task = taskList.get(i);
            task.markAsDone();
            taskList.notifyChange();
            StringBuilder sb = new StringBuilder();
            sb.append("Nice! I've marked this task as done:\n");
            String taskString = "  " + task.toString() + "\n";
            sb.append(taskString);
            return sb.toString();
        } catch (NumberFormatException e) {
            throw new ArgumentNotNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException();
        }
    }

}
