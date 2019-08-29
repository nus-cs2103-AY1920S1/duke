package slave.command;

import slave.elements.Ui;
import slave.elements.TaskList;

import slave.exception.DukeException;

import slave.task.ToDo;

/**
 * Represents a command which adds a to-do into storage and task list.
 */

public class AddToDoCommand extends Command {

    private String task;

    /**
     * Constructor
     *
     * @param task To-do description.
     */
    public AddToDoCommand(String task) {
        this.commandType = CommandType.ADDTODO;
        this.task = task;
    }

    /**
     * Executes the command by adding to-do task to list and print to user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws DukeException For error in adding to taskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ToDo toDoTask = new ToDo(this.task, tasks.getSize() + 1);
        tasks.addToList(toDoTask);
        ui.printAddToDoCommand(toDoTask, tasks);
    }
}
