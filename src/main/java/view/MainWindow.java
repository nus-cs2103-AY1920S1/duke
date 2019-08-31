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
        this.storage = new Storage("/home/leeyiyuan/Projects/duke/data/duke.txt");
        this.parser = new Parser();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (StorageException e) {
            addDukeDialog("Using new task list.");
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
            showError(e.toString());
        } catch (AbortException e) {
            Platform.exit();
        } catch (CommandExecuteException e) {
            showError(e.toString());
        } catch (StorageException e) {
            showError(e.toString());
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

    public void showNumTasks(int num) {
        addDukeDialog(String.format("Now you have %d %s in the list.", num, num == 1 ? "task" : "tasks"));
    }

}
