import duke.command.*;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents an application that manages <code>ToDo</code>s, <code>duke.task.Event</code>s, and <code>Deadline</code>s.
 * A Duke object can add <code>Task</code>s, delete them, mark them as Done, and maintain a history of Tasks entered
 * during earlier execution.
 */
public class Duke extends Application {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {

    }

    /**
     * Constructs a Duke object that saves Tasks in the provided filePath.
     * @param filePath the path of the text file which is a directory for the Tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException de) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DukeException de) {
            ui.showException(de);
        }
    }

    /**
     * Integrates <code>Parser</code>, <code>TaskList</code>, <code>Storage</code>, and <code>Ui</code> to
     * deal with User's commands and accordingly manage database.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ui.showWelcome()); //changed in javafx
        while (true) {
            String command = sc.nextLine();
            System.out.print(ui.printLine()); //changed in javafx
            try {
                Parser commandAnalyzer = new Parser(command);
                System.out.print(Executor.execute(commandAnalyzer, ui, tasks, storage));
            } catch (DukeException de) {
                System.err.println(de.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/mtg-1/OneDrive/Documents/NUS/Y2S1/CS2103/repos/dukerepo/src/main/java/history.txt").run();
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
