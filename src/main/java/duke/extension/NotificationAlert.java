package duke.extension;

import duke.task.Task;
import duke.view.AlertWindow;
import javafx.application.Platform;
import java.util.TimerTask;

public class NotificationAlert extends TimerTask {
    public Task task;

    public NotificationAlert(Task task) {
        this.task = task;
    }

    public void run() {
        Platform.runLater(() -> {
            createAlertWindow();
            task.clearReminder();
        });
    }

    private void createAlertWindow() {
        AlertWindow alertWindow = new AlertWindow();
        alertWindow.setTitle("Reminder");
        alertWindow.setContent(task.toString());
        alertWindow.addImage("/images/duke.png");
        alertWindow.style("/style/stylesheet.css", "alertPane");
        alertWindow.display();
    }

}
