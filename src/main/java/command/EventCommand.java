package command;

import converter.StringDateConverter;
import parser.Storage;
import task.Event;
import task.TaskList;
import ui.Ui;

import java.util.Date;

/**
 * Represent an event to be added
 */
public class EventCommand extends Command {
    private String[] arguments;

    /**
     * @param arguments contains description and date of event
     */
    public EventCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds event to task list
     * Print messages to notify users event has
     * been added to task list
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date at = converter.convertStringToDate(arguments[1]);
            tasks.getTasks().add(new Event(arguments[0], at));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
            System.out.println("Now you have " + tasks.getTasks().size()  + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
