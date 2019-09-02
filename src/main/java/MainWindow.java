import java.io.FileNotFoundException;
import java.io.FileInputStream; 

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

    public Image getImages(String input){
        try{
            return new Image(new FileInputStream(input));
        }catch (FileNotFoundException e){
            System.out.println("Cannot find file");
            return new Image(this.getClass().getResourceAsStream(input));
        }
        
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        userImage = getImages("C:/Users/Tan Ye Kai/Documents/Uni work/Y2S1/CS2103T/Tutorials/Project 1/duke/src/main/resources/images/DaUser.png");
        dukeImage = getImages("C:/Users/Tan Ye Kai/Documents/Uni work/Y2S1/CS2103T/Tutorials/Project 1/duke/src/main/resources/images/DaDuke.png");
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getParser().parse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}