package duke.commands;

import java.io.IOException;


import duke.core.TaskList;
import duke.core.Ui;


import duke.tasks.Event;


/**
 * Represents a command which contains an execute method that adds a event task to the task list.
 * The AddEventCommand object requires the parameters of the task that is to be
 * added to the list.
 */
public class AddEventCommand extends Command{

    private String description;
    private String date;


    /**
     * Initialises the add command which contains the parameters of the task to be created
     * Constructor to creating a command for adding an event task
     * @param description event description
     * @param date date description
     */
    public AddEventCommand(String description, String date) {
        this.description = description;
        this.date = date;
        this.commandType = CommandType.ADDEVENT;
    }

    /**
     * Adds the event task to the task list and prints the result.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @throws IOException Thrown when the new task cannot be added to the file.
     */
    public void execute(TaskList taskList, Ui ui) throws IOException {
        Event task = new Event(this.description,this.date);
        taskList.addToList(task);
        ui.printAddMessage(task, taskList);
    }


}
