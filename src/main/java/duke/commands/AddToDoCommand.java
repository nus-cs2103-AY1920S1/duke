package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

import duke.tasks.ToDo;


/**
 * Represents a command which contains an execute method that adds a to-do task to the task list.
 * The AddToDoCommand object requires the parameters of the task that is to be
 * added to the list.
 */
public class AddToDoCommand extends Command{

    private String [] tokens;

    /**
     * Initialises the add command which contains the parameters of the task to be created
     * @param tokens user input split by space, required for creating a to-do task
     */

    public AddToDoCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDTODO;
    }


    /**
     * Adds the to-do task to the task list and prints the result.
     *
     * @param taskList The main task list of the application.
     * @param ui The main user interface of the application.
     * @throws IOException Thrown when the new task cannot be added to the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ToDo task = ToDo.createToDo(tokens);
        taskList.addToList(task);
        ui.printAddMessage(task, taskList);
    }
    
}
