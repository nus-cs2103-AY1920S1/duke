package duke.view;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the alert window that will be display when {@link duke.extension.NotificationAlert} is called.
 * This is the controller for the alert window displayed in duke.
 */
public class AlertWindow {

    /**
     * This is the alert that will be displayed in the alert window.
     */
    Alert alert;

    /**
     * Constructs a new alert window with a default alert type of <code>Alert.AlertType.INFORMATION</code>. See
     * {@link Alert} for more information.
     */
    public AlertWindow() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
    }

    /**
     * Constructs a new alert window passing in enumerations of {@link javafx.scene.control.Alert.AlertType}.
     * @param alertType the alert type of the alert
     */
    public AlertWindow(Alert.AlertType alertType) {
        this.alert = new Alert(alertType);
    }

    /**
     * Sets the title of the alert window.
     * @param title the title of the alert window
     */
    public void setTitle(String title) {
        alert.setTitle(title);
    }

    /**
     * Sets the content of the alert window.
     * @param message the message content of the alert window
     */
    public void setContent(String message) {
        alert.setHeaderText(message);
    }

    /**
     * Add a customized image to the alert window with the file path of the image.
     * @param path to the image file
     */
    public void addImage(String path) {
        ImageView customImage = new ImageView(new Image(path, 100, 100, false, true));
        alert.setGraphic(customImage);
    }

    /**
     * Style the dialog alert pane by adding stylesheets. The <code>className</code> can be used in the stylesheet
     * to refer to the different components in the {@link DialogPane} to style.
     * @param path to the stylesheet
     * @param className the class name referred in the stylesheet
     */
    public void style(String path, String className) {
        DialogPane alertPane = alert.getDialogPane();
        alertPane.setPrefWidth(580.0);
        alertPane.getStylesheets().add(
            getClass().getResource(path).toExternalForm());
        alertPane.getStyleClass().add(className);
    }

    /**
     * Display the alert window.
     */
    public void display() {
        alert.showAndWait();
    }

}
