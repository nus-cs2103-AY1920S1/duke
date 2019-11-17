package duke.extension;

import duke.view.AlertWindow;
import javafx.application.Platform;
import java.util.TimerTask;

/**
 * This is the notification alert that will pop up when the timer runs out.
 */
public class NotificationAlert extends TimerTask {
    public String taskInfo;

    /**
     * Creates a new notification alert with the task information.
     * @param taskInfo the task information showed during a notification alert
     */
    public NotificationAlert(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    /**
     * Runs and triggers the notification alert when timer runs out.
     */
    public void run() {
        Platform.runLater(() -> {
            createAlertWindow();
        });
    }

    /**
     * Creates a new alert window.
     */
    private void createAlertWindow() {
        AlertWindow alertWindow = new AlertWindow();
        alertWindow.setTitle("Reminder");
        alertWindow.setContent(taskInfo.toString());
        alertWindow.addImage("/images/duke.png");
        alertWindow.style("/style/stylesheet.css", "alertPane");
        alertWindow.display();
    }

}
