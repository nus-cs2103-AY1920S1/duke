// ----- JavaFX -----
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// ----- JavaFX -----

import java.io.IOException;

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

    // ----- JavaFX -----
    // UI components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    // Avatar images
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    // ----- JavaFX -----

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

    // ----- JavaFX -----
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

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            //dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //userInput.clear();
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        // Attach handler on VBox to react to its own size changing and scrolling ScrollPane down
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        //Label userText = new Label(userInput.getText());
        //Label dukeText = new Label(getResponse(userInput.getText()));
        Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
        dialogContainer.getChildren().addAll(
                //new DialogBox(userText, new ImageView(user)),
                //DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getUserDialog(userInput.getText(), userImage),
                //new DialogBox(dukeText, new ImageView(duke))
                //DialogBox.getDukeDialog(dukeText, new ImageView(duke))
                DialogBox.getDukeDialog(getResponse(userInput.getText()), dukeImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
    //private String getResponse(String input) {
        //return "Duke heard: " + input;
        try {
            return run(input);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    // ----- JavaFX -----

//    /**
//     * Starts Duke program.
//     * @throws IOException exception
//     */
//    public void run() throws IOException {
//        ui.showGreetings();
//        boolean inProgram = true;
//        while (inProgram) {
//            try {
//                String userFullInput = ui.readUserInput();
//                Command c = parser.parse(userFullInput, taskList);
//                c.execute(taskList, ui, storage);
//                inProgram = c.toContinueProgram();
//            } catch (DukeException e) {
//                ui.showMessage(e.getMessage());
//            }
//        }
//    }

    // NEW
    public String run(String input) throws IOException {
        try {
            Command c = parser.parse(input, taskList);
            String response = c.execute(taskList, ui, storage);
            return response;
        } catch (DukeException e) {
            ui.showMessage(e.getMessage());
        }
        return "Cannot understand";
    }

    public static void main(String[] args) throws IOException {
        //new Duke("F:\\CS2103\\duke\\data\\duke.txt").run();
        //new Duke().run();
        new Duke();
    }

    public TaskList getTaskList() {
        return taskList;
    }

}