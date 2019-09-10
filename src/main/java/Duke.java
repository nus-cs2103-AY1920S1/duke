import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

/**
 * The main class of the program.
 */
public class Duke extends Application {

    /**
     * The storage containing the file to be modified in the hard drive.
     */
    private Storage storage;

    /**
     * The ui to print out the messages.
     */
    private UI ui;

    /**
     * The list of tasks.
     */
    private TaskList taskList;

    /**
     * The scrolling pane of the application.
     */
    private ScrollPane scrollPane;

    /**
     * The dialog window of the application.
     */
    private VBox dialogContainer;

    /**
     * The box for the user input;
     */
    private TextField userInput;

    /**
     * The send button for user input.
     */
    private Button sendButton;

    /**
     * The scene of the application.
     */
    private Scene scene;

    /**
     * Constructor that creates the main Duke class.
     * @throws Exception Used for when there are any errors.
     */
    public Duke() throws Exception {
        String filepath = "C:\\Users\\robyt\\IdeaProjects\\duke\\src\\main\\Data\\Duke.txt";
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage);
        } catch (FileNotFoundException error) {
            this.taskList = new TaskList();
        }
        this.ui = new UI(taskList);
    }

    /**
     * The main logic of the program that keeps taking user input until the program is exited.
     * @throws Exception Used for when there are any errors.
     */
    public void run() throws Exception {
        ui.printIntro();
        storage.loadTasks();
        boolean programRunning = true;
        while (programRunning) {
            try {
                String input = ui.readLine();
                Command inputCommand = Parser.parse(input);
                inputCommand.execute(taskList, storage, ui);
            } catch (DukeException error) {
                ui.printError(error);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Set up the required components.

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // Step 2. Formatting the window to look as expected.

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        stage.setScene(scene);
        stage.show();

        // Step 3. Add functionality to handle user input.


        sendButton.onMouseClicked()
    }
}
