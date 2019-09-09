package ui.fx;

import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import javafx.fxml.FXML;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;
import ui.input.InputHandler;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DukeMainWindowController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Image userImage;
    private Image dukeImage;

    private InputHandler inputHandler;

    public DukeMainWindowController() {
        userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void configureController(InputHandler input) {
        this.inputHandler = input;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing duke.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (!input.equals("")) {
            printUserMessage(input);

            inputHandler.updateAllListeners(input);

            userInput.clear();
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleUserInput();
        }
    }

    public void printDukeMessage(String message) {
        dialogContainer.getChildren().addAll(FxDialogBox.getDukeDialog(message, dukeImage));
    }

    public void printUserMessage(String message) {
        dialogContainer.getChildren().addAll(
                FxDialogBox.getUserDialog(message, userImage)
        );
    }
}

