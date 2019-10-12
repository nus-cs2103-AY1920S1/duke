import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
    private FXMLLoader fxmlLoader;
    
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialize controls.
     * https://stackoverflow.com/questions/51392203/what-does-initialize-mean-in-javafx
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.STARTMESSAGE, dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setLoader(FXMLLoader fxLoader) {
        fxmlLoader = fxLoader;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        
        if (duke.needReminder(input)) {
            this.showReminder(duke.getReminder(input), duke.getReminderDelay(input));
        }
        userInput.clear();
    }

    /**
     * Schedules a reminder to occur using ScheduledExecutorService.
     * @param reminder String that will be stated when reminder occurs
     * @param delay Number of seconds it is delayed between time reminder is created and time the reminder is to occur
     */
    public void showReminder(String reminder, long delay) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.schedule(
                new Runnable() {
                    public void run() {
                        Platform.runLater(
                                new Runnable() {
                                    public void run() {
                                        //new dialog box in main window to show
                                        dialogContainer.getChildren().addAll(
                                                DialogBox.getDukeDialog(reminder, dukeImage)
                                        );
                                    }
                                }
                        );
                    }
                },
                delay,
                TimeUnit.SECONDS);
    }

    /**
     * Displays string from duke perspective.
     * @param string String that is displayed by duke.
     */
    public void dukeDisplay(String string) {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(string, dukeImage)
        );
    }
    
    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }
}