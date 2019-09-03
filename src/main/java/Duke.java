import duke.command.Command;
import duke.util.*;
import javafx.application.Application;
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

import java.io.FileNotFoundException;

/**
 * This is the driver class that utilises existing classes to receive and respond to the user's commands. It loads and
 * shows the task list from previous storage at the beginning. Util the user enters "bye", it responds to users' input
 * and modifies the current task list. It also informs user about invalid commands from input during the interaction.
 * When the user enters "bye" command, it stores the current task list and terminates the process.
 */
public class Duke extends Application{

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
    }

    /**
     * This is a sole constructor specifying the file path to load data from and write data to. It initialises a
     * <code>Ui</code>  to help with input and output, and a <code>Storage</code> object to loading and storing data.
     *
     * @param filePath a string specifying the path of the file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Starts the program by initialising and run the <code>Duck</code> object. The file path is
     * "/Users/xiaoyu/duke/data/duke.txt" by default.
     *
     * @param args an array of <code>String</code> read from console
     */
    public static void main(String[] args) {
        new Duke("/Users/xiaoyu/duke/data/duke.txt").run();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    private void setUp() {
        ui.showWelcome();

        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

        if (taskList.getTotalTask() > 0) {
            ui.showFullList(taskList);
        } else {
            ui.showNoTask();
        }
    }

    /**
     * Starts the whole interaction process with the user. At first, it loads and shows the task list from previous
     * storage. Util the user enters "bye" commands, it executes the user's every commands. It also informs user about
     * invalid commands from input during the interaction.
     */
    public void run() {

        setUp();

        boolean isExist = false;
        while (!isExist) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, ui, storage);
                isExist = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
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


    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
