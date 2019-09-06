package duke;

import duke.command.Command;
import duke.storage.FileIoException;
import duke.storage.Storage;
import duke.storage.TokenParseError;
import duke.ui.Ui;
import duke.ui.UiCli;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates a new Duke instance, operating with a CLI which will load and save Tasks to the provided filePath.
     * Immediately tries to load the tasks from a filePath on instantiation.
     * A loading error will appear if Duke fails to load the tasks located at the filePath.
     *
     * @param filePath A Path instance for where Tasks should be loaded from and saved to.
     */
    public Duke(String filePath) {
        this(new UiCli(), filePath);
    }

    /**
     * Creates a new Duke instance with a custom Ui implementation, and will load & save Tasks to the provided filePath.
     * Immediately tries to load the tasks from a filePath on instantiation.
     * A loading error will appear if Duke fails to load the tasks located at the filePath.
     *
     * @param ui       A swappable Ui implementation, could be CLI, curses TUI, JavaFX GUI, Swing GUI, AWT GUI etc.
     * @param filePath A Path instance for where Tasks should be loaded from and saved to.
     */
    public Duke(Ui ui, String filePath) {
        this.ui = ui;
        loadStoreFromFile(filePath);
        ui.displayWelcome();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Begins the interactive loop of asking user for commands,
     * executing those commands, and displaying errors if necessary.
     */
    public void run() {
        if (storage == null) {
            throw new IllegalStateException("loadStoreFromFile() needs to be called first");
        }

        if (!(ui instanceof UiCli)) {
            throw new IllegalStateException(
                    "ui must be a UiCli instance as the run() function is intended for cli usage"
            );
        }

        boolean isDone = false;
        while (!isDone) {
            final String input = ((UiCli) ui).nextLine();
            isDone = consumeUserInput(input);
        }
    }

    /**
     * Executes a user command.
     *
     * @param userInput A string representing the user command to execute.
     * @return Whether Duke should exit.
     */
    public boolean consumeUserInput(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (DukeException exc) {
            ui.displayError(exc);
        }
        return false;
    }

    protected void loadStoreFromFile(String filePath) {
        if (ui == null) {
            throw new IllegalStateException("ui must be assigned to display loading errors if needed");
        }

        storage = new Storage(Path.of(filePath));

        try {
            tasks = new TaskList(storage.load());
        } catch (FileIoException | TokenParseError exc) {
            if (!(exc.getCause() instanceof NoSuchFileException)) {
                ui.displayLoadingError(exc);
            }
            tasks = new TaskList();
        }
    }
}
