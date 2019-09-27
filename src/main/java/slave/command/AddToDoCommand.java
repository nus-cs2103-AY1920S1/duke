package slave.command;

import slave.elements.Tags;
import slave.elements.Ui;
import slave.elements.TaskList;

import slave.exception.KappaException;

import slave.task.ToDo;

/**
 * Represents a command which adds a to-do into storage and task list.
 */

public class AddToDoCommand extends Command {

    private String task;
    private Tags tags;

    /**
     * Constructor.
     *
     * @param task To-do description.
     */
    public AddToDoCommand(String task, Tags tags) {
        this.commandType = CommandType.ADDTODO;
        this.tags = tags;
        this.task = task;
    }

    /**
     * Executes the command by adding to-do task to list and print to user.
     *
     * @param tasks List containing current tasks.
     * @param ui User interface.
     * @throws KappaException For error in adding to taskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws KappaException {
        ToDo toDoTask = new ToDo(this.task, tasks.getSize() + 1, this.tags);
        tasks.addToList(toDoTask);
        return ui.printAddToDoCommand(toDoTask, tasks);
    }
}
