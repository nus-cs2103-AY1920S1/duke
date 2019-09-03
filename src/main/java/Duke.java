import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * A personal assistant todo-list tracking chatbot
 *
 * @author Ng Qian Jie Cheryl
 * @version 0.1
 */
public class Duke {
    /**
     * Constant indentation from start of line (formatting)
     */
    private static String INDENT = "    ";
    /**
     * File location for loaded text or new text file containing todo tasks
     */
    private static String FILENAME = "../../../data/duke.txt";

    /**
     * Handles loading tasks from file and saving tasks in file
     */
    private static Storage storage;
    /**
     * Contains task list and operations to modify the list
     */
    private static TaskList tasks;
    /**
     * Deals with interactions with the user
     */
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
