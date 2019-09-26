package duke.commands;

import java.io.IOException;


import duke.core.TaskList;
import duke.core.Ui;


import duke.errors.DukeAssertions;
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
        super(CommandType.COMMAND_ADD_EVENT);
        this.description = description;
        this.date = date;

        DukeAssertions.assertNotNull(description,date);
    }

    /**
     * Adds the event task to the task list and prints the result.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @throws IOException Thrown when the new task cannot be added to the file.
     */
    public String execute(TaskList taskList, Ui ui) throws IOException {
        Event task = new Event(this.description,this.date);

        DukeAssertions.assertNotNull(taskList,ui);

        taskList.addToList(task);
        return ui.printAddMessage(task, taskList);
    }


}
