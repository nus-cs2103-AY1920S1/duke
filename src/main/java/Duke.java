import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class responsible for running Duke Chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor of Duke class.
     */
    public Duke() {
        String filePath = "src/main/data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Method for interpreting Commands and returning Duke's replies to the DialogBox.
     *
     * @param input User input
     * @return Duke's reply message based on input
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            String[] commandArr = fullCommand.split(" ", 2);
            Command c = Parser.parse(commandArr[0].trim());
            if (commandArr.length > 1) {
                return c.execute(storage, taskList, ui, commandArr[1].trim());
            } else {
                return c.execute(storage, taskList, ui, "");
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /*
    /**
     * Method to start up Duke Chatbot.
     *
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, taskList, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    */

    /*
    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").run();
    }
     */


}

