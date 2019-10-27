package commands;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class RemindCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Label secondLabel = new Label("Hello");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 200, 100);
        Stage secondStage = new Stage();
        secondStage.setTitle("Second Stage");
        secondStage.setScene(secondScene);

        //Set position of second window, related to primary window.
        secondStage.setX(0);
        secondStage.setY(0);
        secondStage.show();







        return (tasks.findReminders());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
