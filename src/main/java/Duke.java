import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Creates a new Duke instance which will load and save Tasks to the provided filePath.
     * Immediately tries to load the tasks from a filePath on instantiation.
     * A loading error will appear if Duke fails to load the tasks located at the filePath.
     *
     * @param filePath A Path instance for where Tasks should be loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
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

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Begins the interactive loop of asking user for commands,
     * executing those commands, and displaying errors if necessary.
     */
    public void run() {
        ui.displayWelcome();

        boolean isDone = false;
        while (!isDone) {
            final String input = ui.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isDone = c.isExit();
            } catch (DukeException exc) {
                ui.displayError(exc);
            }
        }
    }
}
