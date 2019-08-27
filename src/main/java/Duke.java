import java.lang.String;
import java.util.Collections;
import java.util.List;

public class Duke {
    protected static final String DUKE_FILE = "duke.txt";

    protected Parser parser;
    protected Storage storage;
    protected TaskList taskList;
    protected Ui ui;

    public Duke(String filePath) {
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    public void run() {
        start();
        runUntilByeCommand();
        exit();
    }

    public void start() {
        ui.showWelcomeMessage();
    }

    public void runUntilByeCommand() {
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = ui.input();
                Command command = parser.parse(input);
                command.execute(storage, taskList, ui);
                isBye = command.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public void exit() {
        ui.showByeMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke(DUKE_FILE).run();
    }
}

