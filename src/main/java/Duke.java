import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Replace this stub with completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Constructor for Duke object.
    */
    public Duke() {
        ui = new Ui();
        storage = new Storage("//Users//chowjiaying//Github//2103T-iP//duke//data//duke.txt");
        tasks = new TaskList(storage.load());
    }

    /*

    public static void main(String[] args) {
        new Duke("//Users//chowjiaying//Github//2103T-iP//duke//data//duke.txt").run();
    }



     * Method that runs the Duke program.

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(userCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }
    */
}
