/**
 * The Duke Program is a bot that help users to manage their schedule.
 *
 * @author : Xu Tunan
 * @Version: v 1.0.0
 * @Since: 29/08/2019
 */
import duke.command.Storage;
import duke.command.Ui;
import duke.list.TaskList;
import duke.excaptions.IllegalDukeArgumentException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Duke extends Application {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     *This constructor is used to check if the text file exists and followed the standard.
     * If yes, it will read the data in the file and transform them to a taskList,
     * while if not, it will create a new taskList to store tasks.
     * @param filePath relative file path to the file that saved the data in the taskList
     */
    private Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui(storage);
        try {
            taskList = new TaskList(storage.textRead());
        } catch (IllegalDukeArgumentException e) {
            ui.showError("Loading Error!\nPlease check the file.");
            taskList = new TaskList();
        }
    }

    /**
     *This method aimed to make use of the readCommand() method in Ui class, and print out a bottom line at last.
     */
    public void run() {
        ui.readCommand();
        ui.showLine();
    }

    /**
     * This method makes use of showWelcome() method in Ui class to print out the greetings.
     */
    private void greeting() {
        ui.showWelcome();
    }

    /**
     * This is the main method to create a new Duke object and run it by steps,
     * which terminate the program when command "bye" appears.
     * @param args unused
     */
    public static void main(String[] args) {
        Duke duke = new Duke("Users/xutunan/duke/duke.txt");
        duke.greeting();
        duke.ui.showLine();
        while (!Ui.getIsExit()) {
            duke.run();
        }

    }
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
    }
}


