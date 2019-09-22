package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.ArgumentNotNumberException;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.TaskDoesNotExistException;
import seedu.duke.exceptions.TaskNotSpecifiedException;
import seedu.duke.task.Task;

public class DeleteCommand extends Command {
    public DeleteCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return delete(arg);
    }

    /**
     * Deletes a task.
     * @param s The index of the task to be deleted, represented as a String.
     * @return The response.
     * @throws DukeException If {@code s} cannot be parsed to an Integer, or if the integer as an index is not
     *         within the {@code TaskList} bound.
     */
    private String delete(String s) throws DukeException {
        assert s != null;
        try {
            if (s.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            int i = Integer.parseInt(s);
            Task task = taskList.get(i);
            taskList.remove(i);
            StringBuilder sb = new StringBuilder();
            sb.append("Noted. I've removed this task:\n");
            String taskString = "  " + task.toString() + "\n";
            sb.append(taskString);
            sb.append(taskList.getListSizeMsg());
            return sb.toString();
        } catch (NumberFormatException e) {
            throw new ArgumentNotNumberException();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskDoesNotExistException();
        }
    }
}
