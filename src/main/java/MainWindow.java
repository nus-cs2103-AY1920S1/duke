import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import slave.elements.Ui;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/doge.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/kermit.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcomeMessage(), dukeImage)
        );
    }

    void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (input.substring(0,3).equals("bye")){
            createExitPopup();
            new ExitProgram();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private void createExitPopup() {
        Stage popup = new Stage();
        popup.setWidth(400);
        popup.setHeight(300);
        ImageView imageView = new ImageView(dukeImage);
        Text text = new Text("Dont worry. I'll keep your tasks safe hehe");
        VBox vbox = new VBox(text, imageView);
        Scene scene = new Scene(vbox, 600, 300);
        popup.setScene(scene);
        popup.setTitle("Goodbye!");
        popup.show();
    }

    private static class ExitProgram {

        Timer timer;

        public ExitProgram() {
            timer = new Timer();
            timer.schedule(new RemindTask(), 3000);
        }

        class RemindTask extends TimerTask {
            public void run() {
                Platform.exit();
                timer.cancel(); //Terminate the timer thread
            }
        }

    }

}
