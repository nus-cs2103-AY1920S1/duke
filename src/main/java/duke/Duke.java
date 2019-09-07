package duke;

import exception.DukeException;
import exception.ExceptionHandler;
import exception.IncorrectDukeCommand;
import exception.InvalidDukeCommand;
import exception.VoidDukeCommand;

import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import misc.Parser;
import misc.Storage;
import misc.Ui;

import task.TaskList;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private ExceptionHandler exceptionHandler;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.exceptionHandler = new ExceptionHandler();
        this.storage = new Storage("data");
    }

    private void run() {
        TaskList taskList;

        try {
            taskList = new TaskList(storage.readSaveFile(), storage);
        } catch (IOException e) {
            taskList = new TaskList(storage);
        }

        if (!taskList.hasTask()) {
            ui.welcome();
        } else {
            ui.welcomeBack();

            try {
                taskList.listAllTasks("list");
            } catch (DukeException e) {
                exceptionHandler.showDukeError(e);
            }

            Ui.showLine();
        }

        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();
        Parser parser = new Parser();

        while (!instruction.equals("bye")) {
            try {
                parser.parseInstruction(instruction, taskList);
            } catch (DateTimeParseException e) {
                exceptionHandler.showParseDateTimeError();
                Ui.showLine();
            } catch (VoidDukeCommand e) {
                exceptionHandler.showVoidDukeCommandError();
                Ui.showLine();
            } catch (IncorrectDukeCommand e) {
                exceptionHandler.showDukeCommandEvaluationError(e);       
                Ui.showLine();
            } catch (InvalidDukeCommand e) {
                exceptionHandler.showUnknownDukeCommandError();
                Ui.showLine();
            } catch (IOException e) {
                exceptionHandler.showDukeIoError();
                Ui.showLine();
            } catch (DukeException e) {
                exceptionHandler.showDukeError(e);   
                Ui.showLine();
            } 

            instruction = sc.nextLine();
        }

        sc.close();
        ui.exit();
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

        stage.setScene(scene);
        stage.show();

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}