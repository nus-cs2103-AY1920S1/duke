import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * The Duke program implements an application that
 * acts as a task manager for users that allows users to write task into a file and
 * update the file accordingly when users have completed their task/wish to delete a
 * task.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private String filePath = "./data/duke.txt";

    /**
     * This storage deals with loading tasks from the file and saving tasks in the file.
     */
    private Storage storage;

    /**
     * ui deals with interactions with the user.
     */
    private Ui ui;

    /**
     * tasks contains the task list of users and operations like adding and removing tasks in the list.
     */
    private TaskList tasks;

    /**
     * Class constructor of <code>Duke</code> that processes user input and outputs the tasks into a data file.
     * If a data file is already created in the given file path, the data file is updated according to
     * the user's input. If file does not exist in the given file path, a new date file is created.
     *
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
    }

    /**
     * This method is used to allow the execution of <code>duke</code> to take place until the user exits
     * the program. It allows the program to keep reading the user's input until the user exits the program.
     */
    public void run() {
        try {
            this.tasks = new TaskList(this.storage.loadFromFile());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.userInput();
                Command c = Parser.parse(userInput);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                System.err.println("Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            this.storage.saveToFile(this.tasks.getAllTasks());
        } catch (DukeException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
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
    }

    /**
     * This is the main method where <code>Duke</code> is instantiated and executed using the <code>run</code> method.
     * A default file path to /duke.txt is provided in the main method.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
