package slave.command;

import slave.elements.Date;
import slave.elements.Ui;
import slave.elements.TaskList;
import slave.exception.DukeException;
import slave.task.Event;

/**
 * Represents a command which adds an event into storage and task list
 */
public class AddEventCommand extends Command{

    String task;
    String date;
    Event eventTask;

    /**
     * Constructor (date doesn't fit the DD/MM/YYYY HHMM format)
     * @param task event description
     * @param date date description
     */
    public AddEventCommand(String task, String date){
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date;
    }

    /**
     * Constructor (date fits into DD/MM/YYYY HHMM format)
     * @param task event description
     * @param date date description
     * @throws DukeException throws invalid date exception of DD/MM/YYYY HHMM format
     */
    public AddEventCommand(String task, Date date) throws DukeException {
        this.commandType = CommandType.ADDEVENT;
        this.task = task;
        this.date = date.convertToString();
    }

    /**
     * executes the command by adding event task to list and print to user
     * @param taskList list containing current tasks
     * @param ui user interface
     * @throws DukeException for error in adding to tasklist
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        this.eventTask = new Event(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(this.eventTask);
        ui.printAddEventCommand(eventTask, taskList);
    }
}
