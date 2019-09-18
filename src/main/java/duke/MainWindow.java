package duke;

import duke.logic.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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

	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
	private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
		// Add welcome message
		dialogContainer.getChildren().add(
				DialogBox.getDukeDialog("Hello! I'm Duke\nWhat can I do for you?", dukeImage));
	}

	/**
	 * Sets Duke controller for MainWindow.
	 *
	 * @param d Sets Duke to input.
	 */
	public void setDuke(Duke d) {
		duke = d;
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 *
	 * If user input's "bye", ByeCommand will execute and window will close after 3 seconds.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		String response = duke.getResponse(input);
		DialogBox user = DialogBox.getUserDialog(input, userImage);
		DialogBox duke = DialogBox.getDukeDialog(response, dukeImage);
		dialogContainer.getChildren().addAll(user, duke);
		userInput.clear();

		if (input.equals("bye")) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					System.exit(0);
				}
			};
			timer.schedule(task, 3000);
		}

	}
}