import java.io.IOException;
import java.util.Optional;

public class Duke {

    private final static String DUKE_TASK_FILE_PATH = ".\\data\\duke.txt";

    private DukeStorage storage;
    private DukeTaskList tasks;
    private DukeUi ui;

    /**
     * Constructor takes in a file path String which specifies the location of the data file to save to/load from.
     * @param filePath Relative/Absolute file path where the data file is stored on the hard disk.
     */
    public Duke(String filePath) {
        ui = new DukeUi();
        storage = new DukeStorage(filePath);
        try {
            tasks = new DukeTaskList(storage.load(ui));
        } catch (IOException ex) {
            ui.displayFileLoadingError();
            tasks = new DukeTaskList();
        }
    }

    /**
     * Run method will first display Duke's welcome message {@link DukeUi#displayWelcomeMessage()} and then run
     * continuously until a {@link DukeCommandExit} is executed.
     */
    public void run() {
        ui.displayWelcomeMessage();
        while (true) {
            String inputLine = ui.readCommand();
            Optional<DukeCommand> command = DukeParser.parseCommand(inputLine, ui);
            if (!command.isEmpty()) {
                command.get().execute(tasks, ui, storage);
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DUKE_TASK_FILE_PATH).run();
    }
}
