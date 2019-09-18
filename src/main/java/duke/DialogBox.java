package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
	@FXML
	private Label dialog;
	@FXML
	private ImageView displayPicture;

	/**
	 * Constructor that creates a DialogBox in Duke.
	 *
	 * @param text String representation for label
	 * @param img Image
	 */
	private DialogBox(String text, Image img) {
		try {
			assert(MainWindow.class.getResource("/view/DialogBox.fxml") != null);
			FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		dialog.setText(text);
		dialog.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		displayPicture.setImage(img);
	}

	/**
	 * Flips the dialog box such that the ImageView is on the left and text on the right.
	 */
	private void flip() {
		ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
		Collections.reverse(tmp);
		getChildren().setAll(tmp);
		setAlignment(Pos.TOP_LEFT);
	}

	/**
	 * Creates DialogBox that represents User input.
	 *
	 * @param text String representation of user input.
	 * @param img Image of user.
	 * @return DialogBox that represents user input.
	 */
	public static DialogBox getUserDialog(String text, Image img) {
		return new DialogBox(text, img);
	}

	/**
	 * Creates DialogBox that represents Duke's response.
	 *
	 * @param text String representation of Duke's response.
	 * @param img Image of Duke.
	 * @return DialogBox that represents Duke's response.
	 */
	public static DialogBox getDukeDialog(String text, Image img) {
		var db = new DialogBox(text, img);
		db.flip();
		return db;
	}
}