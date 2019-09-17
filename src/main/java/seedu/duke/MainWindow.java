package seedu.duke;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static Stage window;
    private Scene scene;


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeImage.png"));
    private Image chatBotImage = new Image(this.getClass().getResourceAsStream("/images/ChatBot_Image.jpg"));

    public static EventHandler<ActionEvent> exitHandler;

    /**
     * Binds the scroll pane to the dialogContainer so as to set up the main layout.
     * Adds the welcome message to the Duke dialog box so that it is shown whenever the application starts.
     */
    @FXML
    public void initialize() {
        assert dukeImage != null : "dukeImage cannot be null";
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.greet(), dukeImage));
    }

    /**
     * Sets the variable duke.
     *
     * @param d the variable duke to be used
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets the variable window.
     *
     * @param s the variable stage to be used
     */
    public void setStage(Stage s) {
        window = s;
    }

    /**
     * Sets the variable scene.
     *
     * @param s the variable scene to be used
     */
    public void setMainScene(Scene s) {
        scene = s;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        if (input.equalsIgnoreCase("show stats")) {
            showStatsScene();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Switches the scene to show bar chart containing the statistics.
     */
    @FXML
    public void showStatsScene() {
        try {
            TaskList.calculateStats();
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/StatisticsScene.fxml"));
            BarChart chart = fxmlLoader.load();
            Scene scene = new Scene(chart);
            window.setScene(scene);
            fxmlLoader.<StatisticsController>getController().setStage(window);
            fxmlLoader.<StatisticsController>getController().setScene(scene);
            window.getIcons().add(chatBotImage);
            window.setTitle("Duke ChatBot");
            window.show();
            TaskList.resetStats();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}