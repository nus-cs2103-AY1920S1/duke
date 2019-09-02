import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * A program named Duke.
 * Something like a Personal Assistant Chatbot that helps
 * a person keep track of various things, except it can only
 * understand archaic text commands of a specific structure.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    // UI components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //public Duke(String filePath) {
    public Duke() {
        ui = new Ui();
        //storage = new Storage(filePath);
        storage = new Storage("F:\\CS2103\\duke\\data\\duke.txt");
        parser = new Parser();
        try {
            // Initialise taskList with AL of tasks
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // If file does not exist, just create new taskList
            ui.showMessage("File in given path does not exist yet. Creating new list of tasks...");
            taskList = new TaskList();
        }
    }

    // Overrides Application#start() method with concrete implementation
    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        // more code to be added here later
    }

    /**
     * Starts Duke program.
     * @throws IOException exception
     */
    public void run() throws IOException {
        ui.showGreetings();
        boolean inProgram = true;
        while (inProgram) {
            try {
                String userFullInput = ui.readUserInput();
                Command c = parser.parse(userFullInput, taskList);
                c.execute(taskList, ui, storage);
                inProgram = c.toContinueProgram();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        //new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
        new Duke().run();
    }


    public TaskList getTaskList() {
        return taskList;
    }

}