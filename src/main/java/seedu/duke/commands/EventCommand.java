package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.EmptyEventArgException;
import seedu.duke.exceptions.EmptyEventDescException;
import seedu.duke.task.Event;
import seedu.duke.task.Task;

public class EventCommand extends Command {
    public EventCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return addEvent(arg);
    }

    /**
     * Adds an Event to the list of tasks.
     * @param desc The description of the Deadline
     * @return The response.
     * @throws DukeException If {@code desc} is empty, or does not contain the second argument separated by
     *         the regex "/at"
     */
    private String addEvent(String desc) throws DukeException {
        assert desc != null;
        try {
            if (desc.isEmpty()) {
                throw new EmptyEventDescException();
            }
            String[] strs = desc.split(Event.REGEX);
            Task task = new Event(strs[0], strs[1]);
            taskList.add(task);
            return taskList.getAddedMsg(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyEventArgException();
        }
    }
}
