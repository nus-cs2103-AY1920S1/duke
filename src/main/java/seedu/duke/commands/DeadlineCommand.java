package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.EmptyDeadlineArgException;
import seedu.duke.exceptions.EmptyDeadlineDescException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return addDeadline(arg);
    }

    /**
     * Adds a Deadline to the list of tasks.
     * @param desc The description of the Deadline
     * @return The response.
     * @throws DukeException If {@code desc} is empty, or does not contain the second argument separated by
     *         the regex "/by"
     */
    private String addDeadline(String desc) throws DukeException {
        assert desc != null;
        try {
            if (desc.isEmpty()) {
                throw new EmptyDeadlineDescException();
            }
            String[] strs = desc.split(Deadline.REGEX);
            Task task = new Deadline(strs[0], strs[1]);
            taskList.add(task);
            return taskList.getAddedMsg(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDeadlineArgException();
        }
    }
}
