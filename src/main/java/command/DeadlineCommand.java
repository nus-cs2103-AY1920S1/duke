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
     * Initializes DeadlineCommand with deadline description and due date.
     *
     * @param arguments contains description and due date
     */
    public DeadlineCommand(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds deadline to task list.
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
            ui.showDeadlineCommand(tasks);
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
        }
    }
}
