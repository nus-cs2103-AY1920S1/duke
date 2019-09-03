import java.util.Arrays;
import java.util.Scanner;
import duke.command.DukeException;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke class. Creates a new Ui, TaskList and Storage object, then runs the main method of the program.
 */
public class Duke extends Application{

    /** Stores the Ui object used to display messages to the user. */
    private Ui ui = new Ui();
    /** Stores the Storage object used to read/write from file. */
    private Storage storage = new Storage("data/duke.txt");;
    /** Stores the TaskList object used to add/delete tasks. */
    private TaskList taskList = new TaskList(storage);

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * This method runs the Duke chat bot. In particular, it prints the intro, and determines which command should
     * be executed based on the user's input.
     */
    private void run() {
        ui.printIntro();

        Scanner scanner = new Scanner(System.in);

        boolean run = true;
        int index;

        while (run) {
            try {
                String[] userInput = Parser.parseUserInput(scanner.nextLine());
                String command = userInput[0];
                String[] params = Arrays.copyOfRange(userInput, 1, userInput.length);

                switch (command) {
                case "bye":
                    ui.printGoodbye();
                    run = false;
                    break;

                case "list":
                    ui.printToUser(taskList.list());
                    break;

                case "done":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to mark as done.");
                    }
                    ui.printToUser(taskList.markAsDone(index));
                    break;

                case "delete":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to delete.");
                    }
                    ui.printToUser(taskList.delete(index));
                    break;

                case "todo":
                    ui.printToUser(taskList.createToDo(params));
                    break;

                case "deadline":
                    ui.printToUser(taskList.createDeadline(params));
                    break;

                case "event":
                    ui.printToUser(taskList.createEvent(params));
                    break;

                case "find":
                    ui.printToUser(taskList.findEvent(params));
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");

                }
            } catch (DukeException e) {
                ui.printErrToUser(e);
            }

        }

        System.exit(0);
    }

    @Override
    public void start (Stage stage) {
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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400, 600);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Scroll down to the end every time the dialog container's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.setPadding(new Insets(10, 5, 0, 5));
        dialogContainer.setSpacing(10);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the input after processing.
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
     * Replace this stub with my own method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing the text to add.
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }

    /**
     * Main class. Sets the filepath for the saving of Duke tasks.
     * @param args Not in use.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
