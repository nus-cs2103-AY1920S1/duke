import duke.command.DukeCommand;
import duke.command.DukeCommandExit;
import duke.util.DukeParser;
import duke.util.DukeStorage;
import duke.util.DukeTaskList;
import duke.util.ui.DukeUi;
import duke.util.ui.DukeUiMessages;
import javafx.application.Application;

import java.io.IOException;
import java.util.Optional;

public class Duke {

    private static final String DUKE_TASK_FILE_PATH = ".\\data\\duke.txt";

    private DukeStorage storage;
    private DukeTaskList tasks;
    private DukeUiMessages ui;

    /**
     * Constructor takes in a file path String which specifies the location of the data file to save to/load from.
     * @param filePath Relative/Absolute file path where the data file is stored on the hard disk.
     */
    public Duke(String filePath) {
        ui = new DukeUiMessages();
        try {
            storage = new DukeStorage(filePath);
            tasks = new DukeTaskList(storage.load(ui));
        } catch (NullPointerException | IOException ex) {
            ui.displayFileLoadingError();
            System.exit(0);
        }
    }

    /**
     * Run method will first display Duke's welcome message {@link DukeUiMessages#displayWelcomeMessage()} and then run
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
        Application.launch(DukeUi.class, args);
        new Duke(DUKE_TASK_FILE_PATH).run();
    }
}
