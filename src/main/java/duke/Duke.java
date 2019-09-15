package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;

import duke.command.TaskList;
import duke.command.Ui;
import duke.command.Parser;
import duke.command.Storage;
import duke.DukeException;
import duke.task.Task;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the main class of the programme.
 */
public class Duke<x> extends Application {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private HashSet<Task> set;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {}
    /**
     * Constructs a duke object that takes in filePath which is the
     * path from which the file is to be loaded.
     * @param filePath The path of the file from which the file is loaded.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
            this.set = this.storage.getSet();
            this.storage.setList(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
            this.storage.setList(taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("tasks.txt");
        duke.run();
    }

    /**
     * Runs the Duke programme.
     */
    public void run() {
        Parser parser = new Parser(this.taskList, this.ui, this.storage, this.set);
        ui.printWelcomeMessage();
    }

    @Override
    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!");
//        Scene scene = new Scene(helloWorld);
//
//        stage.setScene(scene);
//        stage.show();

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
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

    private TaskList getTaskList() {
        return this.taskList;
    }

    private Ui getUi() {
        return this.ui;
    }

    private Storage getStorage() {
        return this.storage;
    }

    private HashSet<Task> getSet() {
        return this.set;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String response;
        Duke duke = new Duke("tasks.txt");

        Storage x = duke.getStorage();
        assert x != null : "storage should not point to null pointer";
        TaskList y = duke.getTaskList();
        assert y != null : "TaskList should not point to null pointer";
        Ui z = duke.getUi();
        assert z != null : "Ui should not point to null pointer";

        Parser parser = new Parser(duke.getTaskList(), duke.getUi(), duke.getStorage(), duke.getSet());

        try {
            response = parser.parseLineInput(input);
            assert !response.equals("") : "Response should not be empty";
            return response;
        } catch (DukeException e) {
            response = e.getMessage();
            return response;
        } catch (ParseException e) {
            response = e.getMessage();
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
