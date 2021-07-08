package duke;

import duke.commands.Command;
import duke.core.Parser;
import duke.core.Storage;
import duke.core.TaskList;
import duke.exceptions.DukeException;
import duke.gui.DialogBox;
import duke.ui.Gui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DukeGui extends Application {

    /**
     * Start Application.
     * @param primaryStage Primary Stage
     */
    private ScrollPane scrollPane;
    private Gui gui;
    private TextField userTextField;
    private Button sendButton;
    private Scene scene;
    private Image avatar;
    private TaskList taskList;
    private Storage storage;

    @Override
    public void start(Stage primaryStage) {

        /** Setup **/
        avatar = new Image(this.getClass().getResourceAsStream("/images/avatar_placeholder.png"));
        storage = new Storage(new File("data"));
        scrollPane = new ScrollPane();
        gui = new Gui(avatar);
        scrollPane.setStyle("-fx-background: #303030");
        scrollPane.setContent(gui);
        userTextField = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userTextField, sendButton);
        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

        /** Layout **/
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        gui.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userTextField.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton,1.0);
        AnchorPane.setLeftAnchor(userTextField, 1.0);
        AnchorPane.setBottomAnchor(userTextField, 1.0);

        /** Functionality **/
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userTextField.getText());
        });
        userTextField.setOnAction((event) -> {
            handleUserInput(userTextField.getText());
        });
        gui.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        /** Display **/
        primaryStage.show();
        initialiseDuke();
    }

    /**
     * Initialise DukeGui.
     */
    public void initialiseDuke() {
        taskList = new TaskList();
        boolean taskFileExists = true;
        boolean storageInitialised = true;

        try {
            storageInitialised = storage.initialise();
        } catch (SecurityException e) {
            gui.echoException(e);
        }

        /** Try to load data */
        try {
            taskList.loadData(storage.getCurrentTasks());
        } catch (FileNotFoundException e) {
            taskFileExists = false;
            try {
                storage.createFile();
            } catch (IOException e2) {
                gui.echoException(e2);
            }
        } catch (DukeException e) {
            gui.echoException(e);
        }

        gui.greet(taskFileExists, storageInitialised);
    }

    /**
     * Handle user input into text box.
     * @param userInput User input
     */
    public void handleUserInput(String userInput) {
        gui.echoUserMessage(userInput);
        getResponse(userInput);
        userTextField.clear();
    }

    /**
     * Interface with Duke components to get response displayed in GUI.
     * @param input Input command
     */
    public void getResponse(String input) {
        try {
            String inputCommand = input; // Initial Input
            Command c = Parser.parseCommand(inputCommand);
            c.execute(storage, taskList, gui);
            if (c.isExit) {
                Platform.exit();
            }
        } catch (IndexOutOfBoundsException e) {
            gui.echoException(new DukeException("Index Out of Bounds Exception Caught"));
        } catch (DukeException e) {
            gui.echoException(e);
        } catch (Exception e) {
            gui.echoException((new DukeException(e.getMessage())));
        }
    }
}
