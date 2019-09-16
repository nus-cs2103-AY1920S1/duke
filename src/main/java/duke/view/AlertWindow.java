package duke.view;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertWindow {
    Alert alert;

    public AlertWindow() {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
    }

    public AlertWindow(Alert.AlertType alertType) {
        this.alert = new Alert(alertType);
    }

    public void setTitle(String title) {
        alert.setTitle(title);
    }

    public void setContent(String message) {
        alert.setHeaderText(message);
    }

    public void addImage(String path) {
        ImageView customImage = new ImageView(new Image(path, 100, 100, false, true));
        alert.setGraphic(customImage);
    }

    public void style(String path, String className) {
        DialogPane alertPane = alert.getDialogPane();
        alertPane.setPrefWidth(580.0);
        alertPane.getStylesheets().add(
                getClass().getResource(path).toExternalForm());
        alertPane.getStyleClass().add(className);
    }

    public void display() {
        alert.showAndWait();
    }
}
