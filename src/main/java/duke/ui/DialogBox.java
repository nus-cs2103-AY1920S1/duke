package duke.ui;

import duke.Duke;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    private FXMLLoader fxmlLoader;

    private DialogBox(String text) {
        try {
            fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    public HBox getRoot() {
        return fxmlLoader.getRoot();
    }

    static DialogBox getUserDialog(String text) {
        return new DialogBox(text);
    }
}