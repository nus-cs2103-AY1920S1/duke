import duke.component.Duke;
import duke.component.Ui;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    //@FML tag makes attribute accessible to FXML
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/husky.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/penguin.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        //Gaurd clause
        if (response.contains("Bye")) {
            System.exit(0);
        }

        //Create and add 2 dialog box (one belonging to user, one belonging to duke) to dialogContainer(VBox)
        addDialogBoxes(input, response);

        //Set preferred height of dialogContainer(VBox)
        setVBoxHeight();
        userInput.clear();

    }

    private void addDialogBoxes(String input, String response) {

        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);

    }

    //Solution below adapted from https://stackoverflow.com/questions/30439848/how-to-adjust-labels-width-to-fit-its-content
    //Solution below also adapted from John Min Kyungho Github: john0227
    private void setVBoxHeight() {
        double height = 0.0;

        //Change the total height of the VBox according to the individual height of elements
        for (Node node : dialogContainer.getChildren()) {
            DialogBox dialogBox = (DialogBox) node;
           height += Region.USE_PREF_SIZE;
        }

        dialogContainer.setPrefHeight(height);

    }


    public void setFirstDialog(Duke duke) {
        this.setDuke(duke);
        Ui ui = new Ui();
        DialogBox welcomeDialog = DialogBox.createDialog(ui.showWelcome(), dukeImage);
        dialogContainer.getChildren().addAll(welcomeDialog);
    }
}