package dukegui.controller;

import duke.module.AutoResponse;

import dukegui.DukeGui;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeGui duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ryan.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/D.png"));

    /**
     * Initializes the properties of children nodes in the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogueContainer.heightProperty());
        AnchorPane.setBottomAnchor(scrollPane, userInput.getPrefHeight() + 2);
        AnchorPane.setRightAnchor(userInput, sendButton.getPrefWidth());
    }

    public void setDuke(DukeGui duke) {
        this.duke = duke;
    }

    /**
     * Greets the user.
     */
    public void greetUser() {
        String dukeText = AutoResponse.DUKE_HELLO.replace("    ", "");
        dialogueContainer.getChildren().addAll(
                DialogueBox.getDukeDialogue(dukeText, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogueContainer.getChildren().addAll(
                DialogueBox.getUserDialogue(input, userImage),
                DialogueBox.getDukeDialogue(response, dukeImage)
        );

        double height = 0.0;
        for (var db : dialogueContainer.getChildren()) {
            DialogueBox box = (DialogueBox) db;
            if (box.getPrefHeight() != -1.0) {
                height += box.getPrefHeight();
            } else {
                height += 100;
            }
        }
        if (dialogueContainer.getPrefHeight() < height) {
            dialogueContainer.setPrefHeight(height);
        }

        userInput.clear();
        if (this.duke.isExit()) {
            this.exit();
        }
    }

    /**
     * Exits after delaying for 1.7 seconds on a different thread
     * to not disrupt any ongoing processes.
     */
    private void exit() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1700);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Platform.exit();
        });
        t.start();
    }

}

