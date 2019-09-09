package command;

import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Represents a todo task to be added.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Initializes ToDoCommand with description of todo Task.
     *
     * @param description contains description of todo task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds todo task to task list.
     * Prints messages to notify users todo task
     * has been added to task list.
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.getTasks().add(new ToDo(this.description));
        ui.showToDoCommand(tasks);
    }
}
