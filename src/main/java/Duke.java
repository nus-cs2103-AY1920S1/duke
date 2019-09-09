import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import command.Command;
import main.DialogBox;
import main.DukeException;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;


/**
 * Main class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/chewie.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/r2d2.png"));

    /**
     * Creates a new Duke object
     */
    public Duke() {
        String filePath = "/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt";
        // Currently hardcoded.
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.dukeEcho(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message to be displayed to the user in String format after parsing the
     * user's full command.
     *
     * @param fullCommand The full command that the user input.
     * @return Duke's response to the full command in String format.
     */
    public String run(String fullCommand) {

        String response = Ui.DIVIDER + "\n";
        try {
            Command c = Parser.parse(fullCommand);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response += ui.getErrorMessage(e.getMessage());
        }
        response += Ui.DIVIDER + "\n";
        return response;
    }


    /*
    public static void main(String[] args) {

        System.out.println("Enter filepath for task list:");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        Duke duke;
        if (filePath.equals("default")) {
            duke = new Duke("/Users/zhangxuan/Desktop/CS2103/duke/data/tasks.txt");
        } else {
            duke = new Duke(filePath);
        }

        duke.run();
    }
    */


    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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
        stage.setMinHeight(700.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 700.0);

        scrollPane.setPrefSize(585, 635);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Display the welcome message
        String uiWelcome = ui.DIVIDER + "\n" + ui.dukeEchoString(ui.getWelcomeMessage())
                + "\n" + ui.DIVIDER;
        Label welcomeLabel = getDialogLabel(uiWelcome);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeLabel, new ImageView(duke)));
        userInput.clear();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        String fullCommand = userInput.getText().trim();
        Label userText = new Label(fullCommand);
        Label dukeText = new Label(run(fullCommand));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if (fullCommand.toLowerCase().equals("bye")) {
            Platform.exit();
        } else {
            userInput.clear();
        }
    }

}