import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * A program that manages and keeps track of a list of tasks.
 * Features include adding and deleting tasks, as well as displaying list of current tasks.
 * Tasks can be marked as done once the user has completed it.
 */
public class Duke extends Application {

    private TaskStorage taskStorage;
    private ArchiveStorage archiveStorage;
    private HashMap<String, TaskList> archives;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private AnchorPane mainLayout;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final int STAGE_HEIGHT = 800;
    private final int STAGE_WIDTH = 600;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/drakeNO.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/drakeYES.jpg"));

    /**
     * Creates a Duke Object.
     */
    public Duke() {
        ui = new Ui();
        taskStorage = new TaskStorage("data/tasks.txt");
        archiveStorage = new ArchiveStorage("data/archives.txt");
        tasks = new TaskList();
        archives = new HashMap<>();
        taskStorage.getTasksFromFile(tasks);
        archiveStorage.getArchivedTasksFromFile(archives);
    }

    /**
     * Processes a String input and returns the required String message.
     *
     * @param input String command from the user.
     * @return The response from Duke to the given input.
     */
    public String run(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null: "Command should not be null"; //Precondition for execute method
            return c.execute(tasks, ui, taskStorage, archiveStorage, archives);
        } catch (DukeException e) {
            return ui.showExceptionError(e);
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        setUpStageComponents(stage);

        //Step 2. Formatting the window to look as expected
        formatComponents(stage, "Duke", STAGE_WIDTH, STAGE_HEIGHT);

        displayLogo();

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Initialises the different components of the Duke GUI.
     *
     * @param stage Stage of the GUI.
     */
    private void setUpStageComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        //The container for the content of the chat to scroll.

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the components in the Duke GUI to specified heights and widths.
     *
     * @param stage Stage of the GUI.
     * @param title Title of the window Duke runs on.
     * @param stageWidth Width of the stage.
     * @param stageHeight Height of the stage.
     */
    private void formatComponents(Stage stage, String title, int stageWidth, int stageHeight) {
        final int SCROLLPANE_WIDTH = STAGE_WIDTH - 15;
        final int SCROLLPANE_HEIGHT = STAGE_HEIGHT - 65;
        final int USERINPUT_WIDTH = STAGE_WIDTH - 75;
        final int SENDBUTTON_WIDTH = 55;

        stage.setTitle(title);
        stage.setResizable(false);
        stage.setMinHeight(stageHeight);
        stage.setMinWidth(stageWidth);

        mainLayout.setPrefSize(stageWidth, stageHeight);

        scrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);;

        userInput.setPrefWidth(USERINPUT_WIDTH);

        sendButton.setPrefWidth(SENDBUTTON_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing. If the user input is the exit command  "bye",
     * closes the application after 2 seconds.
     *
     * @param stage Javafx stage to be closed if input is "bye".
     */
    private void handleUserInput(Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        if (userInput.getText().equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished( event -> stage.close() );
            delay.play();
        }

        userInput.clear();
    }

    private void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String logo2 =
                "DDD    U     U   K     K    EEEEEE \n" +
                "D    D   U     U    K  K     E    \n" +
                "D     D  U     U    K K      EEEE  \n" +
                "D    D   U     U    K  K     E    \n" +
                "DDD    UUUU  K      K   EEEEEE \n";

        Label greetingText = new Label("Hello I'm\n" + logo2 + "\n" + ui.showWelcomeMessage() + "\n\n" +
                taskStorage.getFileAccessStatus() + "\n" + archiveStorage.getFileAccessStatus());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingText, new ImageView(duke)));
    }

    /**
     * Passes the String input from the GUI to the Duke's run method to process it.
     * Returns the response from Duke.
     *
     * @param input String input from the GUI
     * @return String response from Duke to be displayed back to the user.
     */
    private String getResponse(String input) {
        return run(input);
    }

    /**
     * Main Method of Duke object.
     *
     * @param args argument of main method.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

}
