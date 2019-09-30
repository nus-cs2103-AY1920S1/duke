import java.text.ParseException;
import java.io.IOException;

/**
 * Represents a task manager.
 * A Duke object has a file with the list of tasks.
 * Tasks can be added, deleted and updated.
 *
 * @author Michelle Yong
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    /**
     * Creates the task manager with the relevant ui, storage and task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(storage.load());
        } catch (ParseException p) {
            System.out.println("Parse exception occurred. Please ensure correct input format.");
        } catch (IOException io) {
            System.out.println("IOException occurred.");
        }
    }

    public static void main(String[] args) {
        new Duke();
    }

    /**
     * Gets the user interface used.
     *
     * @return The user interface.
     */
    public Ui getUi() {
        return this.ui;
    }

    String getResponse(String input) {
        try {
            Command command = Parser.getCommand(input);
            return command.execute(storage, taskList, ui);
        } catch (DukeException e) {
            return ui.showUnknownCommandError();
        } catch (IOException e) {
            return "IOException error.";
        } catch (ParseException e) {
            return "ParseException error.";
        }
    }
}