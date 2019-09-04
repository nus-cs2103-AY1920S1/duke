import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.EventTarget;

/**
 * A personal assistant todo-list tracking chatbot.
 *
 * @author Ng Qian Jie Cheryl
 * @version 0.1
 */
public class Duke extends Application {
    /**
     * Constant indentation from start of line (formatting).
     */
    private static String INDENT = "    ";
    /**
     * File location for loaded text or new text file containing todo tasks.
     */
    private static String FILENAME = "data/duke.txt";

    /**
     * Handles loading tasks from file and saving tasks in file.
     */
    private static Storage storage;
    /**
     * Contains task list and operations to modify the list.
     */
    private static TaskList tasks;
    /**
     * Deals with interactions with the user.
     */
    private Ui ui;

    /**
     * Main initializer of Duke bot.
     * @param filePath Location for file to be saved
     */
    public Duke(String filePath) {
        ui = new Ui(INDENT);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception err) {
            ui.printError(err.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method running Duke class.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
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

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
