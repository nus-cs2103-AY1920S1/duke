package seedu.duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control using FXML that represents a dialog box containing the Duke Logo.
 * Dialog box consists of an ImageView to represent the speaker's face another ImageView to represent the Duke Logo.
 */
public class LogoDialog extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private ImageView logo;

    /**
     * Creates an instance of LogoDialog that stores two Images representing the Duke logo and avatar respectively.
     * @param logo Image representing the Duke logo.
     * @param img An avatar representing the party who sent the given input message.
     */
    private LogoDialog(Image logo, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LogoDialog.fxml"));
            assert fxmlLoader != null : "fxmlLoader for LogoDialog is null";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayPicture.setImage(img);
        this.logo.setImage(logo);
    }

    /**
     * Returns a LogoDialog object that represents Duke's part of the dialog.
     * @param logo Image representing the Duke logo.
     * @param img Image representing Duke in the chat application.
     * @return LogoDialog object representing Duke's part of the dialog.
     */
    public static LogoDialog getDukeLogo(Image logo, Image img) {
        assert img != null : "Duke profile image is null.";
        assert logo != null : "Duke logo image is null.";
        var db = new LogoDialog(logo, img);
        return db;
    }
}