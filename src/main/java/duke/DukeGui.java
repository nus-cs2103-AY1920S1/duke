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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private Gui dialogContainer;
    private TextField userTextField;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("../images/avatar_placeholder.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("../images/avatar_placeholder.png"));
    private TaskList taskList;
    private Storage storage = new Storage(new File("data/tasks.txt"));


    @Override
    public void start(Stage primaryStage) {
        /** Setup **/
        scrollPane = new ScrollPane();
        dialogContainer = new Gui();
        scrollPane.setContent(dialogContainer);
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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userTextField.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userTextField , 1.0);
        AnchorPane.setBottomAnchor(userTextField, 1.0);

        /** Functionality **/
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userTextField.getText());
        });

        userTextField.setOnAction((event) -> {
            handleUserInput(userTextField.getText());
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        initialiseDuke();
    }

    public void initialiseDuke() {
        taskList = new TaskList();

        /** Try to load data */
        try {
            taskList.loadData(storage.getTaskList());
            dialogContainer.greet(true);
        } catch (FileNotFoundException e) {
            dialogContainer.greet(false);
            try {
                storage.createFile();
            } catch (IOException e2) {
                dialogContainer.sendDukeMessage(e2.getMessage());
            }
        } catch (DukeException e) {
            dialogContainer.sendDukeMessage(e.getMessage());
        }

    }



    public void handleUserInput(String userInput) {
        Label userText = new Label(userInput);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
        getResponse(userInput);
        userTextField.clear();
    }

    private void getResponse(String input) {
        try {
            String inputCommand = input; // Initial Input
            Command c = Parser.parseCommand(inputCommand);
            c.execute(storage, taskList, dialogContainer);
            if(c.isExit) {
                Platform.exit();
            }
        } catch(IndexOutOfBoundsException e) {
            dialogContainer.echoException(new DukeException("Index Out of Bounds Exception Caught"));
        }
        catch (DukeException e) {
            dialogContainer.echoException(e);
        } catch(Exception e) {
            dialogContainer.echoException((new DukeException(e.getMessage())));
        }
    }
}
