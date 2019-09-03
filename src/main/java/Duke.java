import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static String INDENT = "    ";
    private static String FILENAME = "../../../data/duke.txt";

    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(INDENT);
        try {
            storage = new Storage(filePath);
        } catch(FileNotFoundException | UnsupportedEncodingException err) {
            ui.showFileMissingError();
        }
        try {
            tasks = new TaskList(storage.load());
        } catch(Exception err) {
            ui.printError(err.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = Parser.parse(parser.getCommand(), parser.getDetail(), INDENT);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILENAME).run();
    }
}
