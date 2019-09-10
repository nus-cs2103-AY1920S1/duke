package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Encapsulates a command used to confirm the date of an event which has tentative dates.
 */
public class ConfirmEventDateCommand extends Command {
    private int taskIndex;
    private int dateIndex;

    /**
     * Creates a ConfirmEventDateCommand object.
     *
     * @param taskIndex the index of the target event.
     * @param dateIndex the index of the date to confirm, starts from 1.
     */
    public ConfirmEventDateCommand(int taskIndex, int dateIndex) {
        this.taskIndex = taskIndex;
        this.dateIndex = dateIndex;
    }

    /**
     * Sets the confirmed date of the event, choosing from one of its tentative dates.
     *
     * @param database the database of duke.
     * @param tasksList the tasks list of duke.
     * @return a message showing that the confirmed date of the event has been set.
     * @throws DukeException if the index of the event and/or the index of the date is lesser than 1 or greater than
     *     the total number of tasks and the total number of tentative dates respectively.
     */
    public String execute(DukeDatabase database, TaskList tasksList) throws DukeException {
        Task task = tasksList.getTask(taskIndex);

        if (!(task instanceof Event)) {
            throw new DukeException("The target task is not an event!");
        }

        Event event = (Event) task;
        Event newEvent = ((Event) task).setDate(dateIndex);
        tasksList.setTask(taskIndex, newEvent);

        return String.format("Got it, I've set the confirmed date of the event:\n%s\n", newEvent.toString());
    }
}
