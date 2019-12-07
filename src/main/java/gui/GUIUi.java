package gui;

import com.util.ui.Ui;
import javafx.scene.layout.VBox;

public class GUIUi extends Ui {

    private VBox dialogContainer;

    public GUIUi(VBox dialogContainer) {
        super();
        this.dialogContainer = dialogContainer;
    }

    @Override
    public void showMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message));
    }
}
