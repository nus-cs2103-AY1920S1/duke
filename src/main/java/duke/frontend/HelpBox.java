package duke.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpBox extends HBox {
	@FXML
	private Label help;
	@FXML
	private ImageView helpPicture;
	
	/**
	 * Constructor to generate new help box for Duke chat bot.
	 */
	HelpBox(String text, Image img) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpBox.fxml"));
			fxmlLoader.setController(this);
			fxmlLoader.setRoot(this);
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		help.setText(text);
		help.setWrapText(true);
		helpPicture.setImage(img);
		setAlignment(Pos.TOP_LEFT);
	}
	
	/**
	 * Generate a GUI dialog box with help messages.
	 *
	 * @return Dialog box with help response and image.
	 */
	static HelpBox getHelpBox(String text, Image img) {
		return new HelpBox(text, img);
	}
	
}