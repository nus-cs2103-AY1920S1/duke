package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Event;
import models.Task;

/**
 * Represents the characteristics of an EventCommand.
 */
public class EventCommand extends Command {

    /**
     * Constructor of an EventCommand.
     *
     * @param description description of the event task.
     */
    public EventCommand(String description) {

        super(description);

    }

    /**
     * Execution of an event Command. In this case, an Event task would be created and added to the current
     * arraylist.
     *
     * @param taskList where the new Event task is added (if there are no exceptions) to.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException thrown when an input to create event command is invalid.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);

        String wholeTask = this.descriptionOfTask.trim();
        int index = wholeTask.indexOf('/');


        //what the task is
        String description = wholeTask.substring(0, index).trim();
        //when it is due by
        String date = wholeTask.substring(index + 4).trim();
        int int_Priority = date.indexOf('*');

        //execution
        Task newEvent;
        if(int_Priority >= 0) {
            String cleanDate = date.substring(0, int_Priority) + date.substring(int_Priority + 1);
            newEvent = new Event(description, cleanDate);
            newEvent.markAsPriority();
            taskList.addPriorityTask(newEvent);
        } else {
            newEvent = new Event(this.descriptionOfTask, date);
            taskList.addTask(newEvent);
        }
        ui.displayAddingOfTask(newEvent, taskList.getSize());
        storage.saveToDataFile(taskList);
    }

    /**
     * Handles the error and checks if it is valid for execution.
     *
     * @throws DukeException thrown if event description input is empty or does not have /at in it.
     */
    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!this.descriptionOfTask.contains("/at")) {
            throw new DukeException(" ☹ OOPS!!! Event input should include '/at'.");
        }
    }
}
