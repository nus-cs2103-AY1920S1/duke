package command;

import converter.StringDateConverter;
import parser.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.util.Date;

/**
 * Represents a deadline task to added.
 */
public class DeadlineCommand extends Command {
    private String[] arguments;

    /**
     * @param arguments contains description and date of deadline.
     */
    public DeadlineCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds deadline to task list
     * Prints messages to notify users deadline has been
     * added to task list
     *
     * @param tasks contains task list
     * @param ui deals with interaction with users
     * @param storage deals with loading and saving of task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            StringDateConverter converter = new StringDateConverter();
            Date by = converter.convertStringToDate(arguments[1].trim());
            tasks.getTasks().add(new Deadline(arguments[0], by));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.getTasks().get(tasks.getTasks().size() - 1));
            System.out.println("Now you have " + tasks.getTasks().size()
                    + " tasks in the list.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
