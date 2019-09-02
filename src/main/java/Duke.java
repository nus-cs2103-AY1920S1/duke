import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Duke extends Application {
    private TodoList tasks;
    private Storage storage;
    private Ui ui;
    private static final String filePath = "/home/dingyuchen/cs2103/duke/src/main/data/duke.ser";

    public Duke(String s) {
        storage = new Storage(s);
        ui = new Ui();
        tasks = storage.load();
    }

    public Duke() {
        this(filePath);
    }

    private void run() {
        this.ui.welcome();

        while (true) {
            try {
                Command command = this.ui.getUserInput();
                String response = command.run(tasks, storage);
                this.ui.printResponse(response);
                if (command.isExit()) {
                    break;
                }
            } catch (DukeException e) {
                this.ui.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(filePath);
        duke.run();
    }

    @Override
    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout, 300, 500);

        stage.setScene(scene);
        stage.show();
    }
}
