package duke.ui;

import duke.DukeException;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;


import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * User interface element to display the dialog boxes of the user and duke.
 */
public class DialogContainer extends ScrollPane {
    private VBox dialog;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/dog.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/fatCat.png"));


    /**
     * Constructs the dialog container.
     */
    public DialogContainer() {
        dialog = new VBox();
        dialog.heightProperty().addListener((observable -> this.setVvalue(1.0)));

        setContent(dialog);;

        setPrefSize(400, 560);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        setFitToWidth(true);
    }

    /**
     * Displays the user's message and duke's response message
     * @param call The user's message to duke
     * @param response Duke's response to the user
     */
    public void displayDialog(String call, String response) {
        Label callText = new Label(call);
        callText.setTextFill(Color.rgb(255,255,255));
        Label responseText = new Label(response);
        responseText.setTextFill(Color.rgb(255,255,255));

        dialog.getChildren().addAll(
                DialogBox.getUserDialog(callText, new ImagePattern(user)),
                DialogBox.getDukeNormalDialog(responseText, new ImagePattern(duke))
        );
    }

    /**
     * Displays the user's message and duke's error response message
     * @param call The user's message to duke which causes an error
     * @param e Duke's error response message
     */
    public void displayError(String call, DukeException e) {
        Label callText = new Label(call);
        callText.setTextFill(Color.rgb(255,255,255));

        Label errorMsg = new Label(e.getMessage());
        errorMsg.setTextFill(Color.rgb(255,255,255));

        dialog.getChildren().addAll(
                DialogBox.getUserDialog(callText, new ImagePattern(user)),
                DialogBox.getDukeErrorDialog(errorMsg, new ImagePattern(duke))
        );
    }
}