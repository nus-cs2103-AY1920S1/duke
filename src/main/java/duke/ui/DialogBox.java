package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

/**
 * FXML abstraction of a dialogBox.
 * Consists of an ImageView to represent the user or duke's image and a label
 * containing response from duke.
 */
class DialogBox extends AnchorPane {
    /** JavaFX Label control containing the dialog message. */
    @FXML
    private Label dialog;
    /** JavaFX ImageView control for displaying the user or duke's image. */
    @FXML
    private ImageView displayPicture;
    /** Amount of space between the dialog and displayPicture containers. */
    private static final int DIALOG_IMAGE_SPACE = 10;
    /** Amount of space between the displayPicture and application's border. */
    private static final double WINDOW_IMAGE_SPACE = 1.0;

    /**
     * Private constructor of dialog box for initialising the Label and
     * ImageView controls.
     * Also flips the horizontal orientation to left-to-right if specified.
     *
     * @param text String dialog message for the Label control.
     * @param image JavaFX Image for the ImageView control.
     * @param shouldFlip Boolean of whether the dialogBox should be aligned left-to-right.
     */
    private DialogBox(String text, Image image, boolean shouldFlip) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);

        if (shouldFlip) {
            setFlippedDialogStyle();
        } else {
            setNormalDialogStyle();
        }
    }

    /**
     * Sets the styles for a normal dialog box.
     */
    private void setNormalDialogStyle() {
        dialog.setStyle(dialog.getStyle()
                + "-fx-border-width: 0 2 0 0; -fx-border-color:#393654;");
        AnchorPane.setRightAnchor(dialog,
                displayPicture.getBoundsInLocal().getMaxX() + DIALOG_IMAGE_SPACE);

        this.setStyle(this.getStyle()
                + "-fx-border-color:#aaaaaa; -fx-background-color:#4e8c91;");
    }

    /**
     * Sets the styles for a flipped dialog box.
     */
    private void setFlippedDialogStyle() {
        AnchorPane.setLeftAnchor(displayPicture, WINDOW_IMAGE_SPACE);
        AnchorPane.setRightAnchor(displayPicture, null);
        AnchorPane.setTopAnchor(displayPicture,
                displayPicture.getParent().getBoundsInLocal().getHeight() / 2 - DIALOG_IMAGE_SPACE);

        dialog.setStyle(dialog.getStyle()
                + "-fx-border-width: 0 0 0 2; -fx-border-color:#ddd6c7;");
        AnchorPane.setLeftAnchor(dialog,
                displayPicture.getBoundsInParent().getMaxX() + DIALOG_IMAGE_SPACE);
        AnchorPane.setRightAnchor(dialog, WINDOW_IMAGE_SPACE);
        dialog.setAlignment(Pos.CENTER_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);

        this.setStyle(this.getStyle()
                + "-fx-border-color:#f2eada; -fx-background-color:#82c9c7;");
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text String dialog message from the user.
     * @param image JavaFX image of user for the ImageView control.
     * @return User DialogBox instance.
     */
    static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image, false);
    }

    /**
     * Creates a dialog box for duke.
     *
     * @param text String dialog message from duke.
     * @param image JavaFX image of duke for the ImageView control.
     * @return Duke DialogBox instance.
     */
    static DialogBox getDukeDialog(String text, Image image) {
        return new DialogBox(text, image, true);
    }
}