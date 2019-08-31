package duke;

import javafx.scene.layout.VBox;

public class GuiUi extends Ui {
    private VBox dialogContainer;

    public GuiUi(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    @Override
    protected void showError(String errorMessage) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(prefix + " " + errorMessage));
    }

    @Override
    protected void showMessage(String message) {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(message));
    }

    @Override
    public void showLine() { }
}
