/**
 * This class represents a specific command of exiting from Duke.
 */
package duke.commands;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public class ExitCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ExitCommand() {}

    /**
     * This method prints the exit line from Duke and closes the chatbot.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.ui.printLine("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }

}
