package duke;


import java.io.FileNotFoundException;
import java.io.FileInputStream;

import duke.exceptions.DukeException;
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
    private Duke duke;
    private Image userImage;
    private Image dukeImage;

    /**
     * A workaround for getResourceAsStream().
     * Uses FileInputStream rather than the above method.
     * 
     * @param input
     * @return a new Image which can be added to the dialog box control
     */
    public Image getImages(String input){
//        try{
//            return new Image(new FileInputStream(input));
//        }catch (FileNotFoundException e){
//            System.out.println("Cannot find file");
//            return new Image(this.getClass().getResourceAsStream(input));
//        }
        return new Image(this.getClass().getResourceAsStream(input));
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        try{
            userImage = getImages("/images/DaUser.png");
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            dukeImage = getImages("/images/DaDuke.png");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try{
            response = duke.getParser().parse(input);
        }catch(DukeException e){
            response = e.toString();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}