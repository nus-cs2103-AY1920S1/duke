package SerSnapsalot.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import SerSnapsalot.ui.Ui;
import SerSnapsalot.duke.Duke;

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
	private Image tonyImage = new Image(this.getClass().getResourceAsStream("/images/tony.jpg"));
	private Image tomImage = new Image(this.getClass().getResourceAsStream("/images/spiderman.jpg"));
	private Image theSnap = new Image(this.getClass().getResourceAsStream("/images/snap2.jpg"));
	private Image tonyDead = new Image(this.getClass().getResourceAsStream("/images/tonydead.jpg"));
	private Image tomDead = new Image(this.getClass().getResourceAsStream("/images/spideydead.jpg"));
	private Image tomDust = new Image(this.getClass().getResourceAsStream("/images/spiderdust.jpg"));
	private Image userImage = tomImage;
	private Image dukeImage = tonyImage;
	private boolean hasSnap = false;

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
		dialogContainer.getChildren().addAll(
				DialogBox.getDukeDialog(new Ui().showWelcome(), tonyImage)
		);
	}

	public void setDuke(Duke d) {
		duke = d;
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 * Solution adapted from Project Group
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		String response = duke.getResponse(input);
		if(input.equals("snap")) {
			snap();
		} else if (input.equals("bye")) {
			addUserDialog("Bye Mr. Stark!", userImage);
			addDukeDialog(response, dukeImage);
			Timer timer = new Timer();
			TimerTask delay = new TimerTask() {
				public void run() {
					System.exit(0);
				}
			};
			timer.schedule(delay, 1111);
		} else {
			addUserDialog(input, userImage);
			addDukeDialog(response, dukeImage);
		}
	}

	private void addUserDialog (String input, Image image) {
		dialogContainer.getChildren().addAll(
			DialogBox.getUserDialog(input, image)
		);
		userInput.clear();
	}
	private void addDukeDialog (String input, Image image) {
		dialogContainer.getChildren().addAll(
			DialogBox.getDukeDialog(input, image)
		);
		userInput.clear();
	}

	private void snap() {
		addUserDialog("Snap me daddy", userImage);
		addDukeDialog("I am Ironman", theSnap);
		hasSnap = (!hasSnap);
		if (hasSnap) {
			addUserDialog("Mr Stark, I don't feel so good", tomDead);
			String tonyDyingMessage = "";
			tonyDyingMessage += "If we can't protect the world, you can be damn sure we'll avenge it.";
			tonyDyingMessage += "\nBy taking half the tasks with us.";
			addDukeDialog(tonyDyingMessage, tonyDead);
			userImage = tomDust;
			dukeImage = tonyDead;
		} else {
			userImage = tomImage;
			dukeImage = tonyImage;
		}
	}
}

