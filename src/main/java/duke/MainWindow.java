package duke;

import duke.parser.IncorrectFileFormatException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;

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
    private Duke duke;
    private Image userImage;
    private Image dukeImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    public void setDuke(Duke d) {
        duke = d;
        String loadingErrorMessage = "";
        boolean isErrorOccurred = false;

        // Perform loading of tasks from disk
        try{
            duke.performDukeStartup();
        } catch (InvalidPathException i) {
            isErrorOccurred = true;
            loadingErrorMessage = i.getMessage();
        } catch (IncorrectFileFormatException f) {
            loadingErrorMessage = f.getMessage();
        } catch (NullPointerException n) {
            loadingErrorMessage = n.getMessage();
        } catch (FileNotFoundException z){
            loadingErrorMessage = z.getMessage();
        }

        if(isErrorOccurred) {
            dialogContainer.getChildren().add(new DialogBox(loadingErrorMessage, dukeImage));
        }

        // Throw initial duke greeting
        dialogContainer.getChildren().add(new DialogBox(duke.getDukeWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if(input.toLowerCase().equals("help")){
            handleHelpPage();
        } else{
            handleOperations(input);
        }
    }

    /*
        Handles the display for help page and helpful advice for users.
     */
    private void handleHelpPage(){
       Image jigglypuff = new Image(this.getClass().getResourceAsStream("/images/jigglypuff.png"));
        dialogContainer.getChildren().addAll(new HelpBox("Welcome to help page!", jigglypuff));
        userInput.clear();

    }

    /*
        Handles usual operations other than help page.
     */
    private void handleOperations(String input){
        String response = duke.getDukeResponse(input);

        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage), DialogBox.getDukeDialog(response, dukeImage));

    }
}