package slave.command;

import slave.elements.Date;
import slave.elements.Ui;
import slave.elements.TaskList;

import slave.exception.DukeException;

import slave.task.Deadline;

/**
 * Represents a command which adds a deadline into storage and task list.
 */
public class AddDeadlineCommand extends Command {

    private String task;
    private String date;

    /**
     * Constructor (date doesn't fit the DD/MM/YYYY HHMM format).
     *
     * @param task Deadline description.
     * @param date Date description.
     */
    public AddDeadlineCommand(String task, String date) {
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date;
    }

    /**
     * Constructor (date fits into DD/MM/YYYY HHMM format).
     *
     * @param task Task description
     * @param date Date description
     * @throws DukeException Throws invalid date exception of DD/MM/YYYY HHMM format.
     */
    public AddDeadlineCommand(String task, Date date) throws DukeException {
        this.commandType = CommandType.ADDDEADLINE;
        this.task = task;
        this.date = date.convertToString();
    }

    /**
     * Executes the command by adding deadline task to list and print to user.
     *
     * @param taskList List containing current tasks.
     * @param ui User interface.
     * @throws DukeException For error in adding to taskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        Deadline deadlineTask = new Deadline(this.task, taskList.getSize() + 1, this.date);
        taskList.addToList(deadlineTask);
        ui.printAddDeadlineCommand(deadlineTask, taskList);
    }
}
