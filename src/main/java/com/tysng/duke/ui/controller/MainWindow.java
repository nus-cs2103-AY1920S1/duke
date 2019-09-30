package com.tysng.duke.ui.controller;

import com.tysng.duke.exception.CommandException;
import com.tysng.duke.service.DukeService;
import com.tysng.duke.service.Parser;
import com.tysng.duke.service.command.Command;
import com.tysng.duke.service.command.ExitCommand;
import com.tysng.duke.ui.Response;
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
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private DukeService dukeService;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Response.newSplash().toDialogue(), dukeImage)
        );
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(Response.newGreetings().toDialogue(), dukeImage)
        );
    }

    public void setDukeService(DukeService d) {
        dukeService = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        // add response
        Response resp;
        try {
            Command command = Parser.newCommand(input);
            if (command instanceof ExitCommand) {
                Platform.exit();
            }

            resp = dukeService.handle(command);

        } catch (CommandException e) {
            resp = Response.newException(e);
        }

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(resp.toDialogue(), dukeImage)
        );
        userInput.clear();
    }


}