package com.leeyiyuan.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import com.leeyiyuan.ui.UserOutputInterface;
import com.leeyiyuan.command.AbortException;
import com.leeyiyuan.command.Command;
import com.leeyiyuan.command.CommandExecuteException;
import com.leeyiyuan.command.format.CommandParseException;
import com.leeyiyuan.command.format.Parser;
import com.leeyiyuan.storage.Storage;
import com.leeyiyuan.storage.StorageException;
import com.leeyiyuan.task.TaskList;


import java.io.File;
import java.net.URLDecoder;
import java.net.URISyntaxException;

public class MainWindow extends VBox implements UserOutputInterface {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    
    private Storage storage;
    private Parser parser;
    private TaskList tasks;

    @FXML
    private void initialize() {

        String storagePath = "";
        try {
            storagePath = new File(MainWindow.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                    .getPath();
        } catch (URISyntaxException e) {
            // Suppress format exception since this assumes format is valid.
        }
        storagePath += ".dukedata.txt";

        this.storage = new Storage(storagePath);
        this.parser = new Parser();

        addDukeDialog("Hello! I'm Duke\nWhat can I do for you?");
        addDukeDialog("By the way, I'm using this file to store your data:\n" + storagePath);

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (StorageException e) {
            this.tasks = new TaskList();
        }

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        addUserDialog(input);

        try {
            Command command = this.parser.parse(input);
            command.execute(this.tasks, this, this.storage);
        } catch (CommandParseException e) {
            showError(e.getMessage());
        } catch (AbortException e) {
            Platform.exit();
        } catch (CommandExecuteException e) {
            showError(e.getMessage());
        } catch (StorageException e) {
            showError(e.getMessage());
        }

        userInput.clear();
    }

    private void addDukeDialog(String text) {
        DialogBox db = new DialogBox(text, this.dukeImage);
        db.flip();
        this.dialogContainer.getChildren().add(db);
    }
    
    private void addUserDialog(String text) {
        DialogBox db = new DialogBox(text, this.userImage);
        this.dialogContainer.getChildren().add(db);
    }

    public void showLine(String text) {
        addDukeDialog(text);
    }

    public void showError(String text) {
        addDukeDialog("Error: " + text);
    }

}
