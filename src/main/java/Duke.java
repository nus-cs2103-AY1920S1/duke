import duke.component.Ui;
import duke.component.TaskList;
import duke.database.Storage;
import duke.command.Command;
import duke.component.Parser;
import duke.exception.DukeException;

public class Duke {
    /** Database of the Duke Program */
    private Storage storage;

    /** List of task */
    private TaskList tasks;

    /** Ui of the Duke Program */
    private Ui ui;

    /**
     * Duke program to initalise the Database, Ui and get the task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(ui);
        storage.initialise();
        tasks = new TaskList(storage.getSavedTask());
    }

    /**
     * To stimulate the Duke Program to run.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showSpace();
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main function to start Duke Database.
     * @param args Arguments enter by user.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}