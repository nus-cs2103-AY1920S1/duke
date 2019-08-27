package command;

import parser.Storage;
import task.TaskList;
import task.ToDo;
import ui.Ui;

/**
 * Represents a todo task to be added
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * @param description contains description of todo task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds todo task to task list
     * Prints messages to notify users todo task
     * has been added to task list
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Got it. I've added this task:");
        tasks.getTasks().add(new ToDo(this.description));
        System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
        System.out.println("Now you have " + tasks.getTasks().size()
                + " tasks in the list.");
    }
}
