import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
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
