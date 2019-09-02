import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Class to represent Duke. */
public class Duke extends Application {

    // class variables
    private UI ui;
    private Parser parser;
    private Storage storage;
    private TaskList tl;

    // variables required for JavaFX execution
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Terminator.jpg"));

    /**
     * Class constructor for the Duke class.
     */
    public Duke() {
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage("data/duke.txt");
        try {
            this.tl = new TaskList(storage.readTasksFromFile());
        } catch (DukeException e) {
            this.ui.printError(e);
            System.exit(0);
        }
    }

    /**
     * Method which runs and performs user input and task addition.
     */
    public void run() {
        this.ui.printWelcome();
        this.ui.showTaskList(this.tl);

        String input = this.ui.takeInput();
        while (input != null) {
            try {
                Task task;
                switch (this.parser.parseCommand(input)) {
                case "bye":
                    this.ui.printExit();
                    System.exit(0);
                    break;
                case "list":
                    this.ui.showTaskList(this.tl);
                    break;
                case "done":
                    task = this.tl.taskDone(this.parser.parseInteger(input.split(" ")[1]));
                    this.ui.showTaskMarkedDone(task);
                    break;
                case "delete":
                    task = this.tl.removeTask(this.parser.parseInteger(input.split(" ")[1]));
                    this.ui.showTaskDeletion(task, this.tl);
                    break;
                case "todo":
                    task = this.parser.parseTodo(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, this.tl);
                    break;
                case "event":
                    task = this.parser.parseEvent(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, tl);
                    break;
                case "deadline":
                    task = this.parser.parseDeadline(input.split(" ", 2)[1]);
                    tl.addTask(task);
                    this.ui.showTaskAddition(task, tl);
                    break;
                case "find":
                    TaskList newTl = tl.findTasks(input.split(" ", 2)[1]);
                    this.ui.showTaskList(newTl);
                    break;
                default:
                    this.ui.printError(new DukeException("Unknown command. :("));
                    break;
                }
                this.storage.writeTasksToFile(this.tl);
                input = this.ui.takeInput();
            } catch (DukeException e) {
                this.ui.printError(e);
            }
        }

        this.ui.printExit();
        System.exit(0);
    }

    /**
     * Main method for the class.
     * @param args Arguments passed while execution.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Function which helps execute JavaFX.
     */
    @Override
    public void start(Stage stage) {
        // Step 1. Settingu p requried components.

        // The container for the content of the chat to scroll.
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        this.dialogContainer.setSpacing(10);
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);

        this.scene = new Scene(mainLayout);

        stage.setScene(scene);

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
        
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        userInput.setPrefWidth(325.0);
        
        sendButton.setPrefWidth(55.0);
        
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        this.userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Show the stage.
        stage.show();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Terminator says: " + input;
    }
}
