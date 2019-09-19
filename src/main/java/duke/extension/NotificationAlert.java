package duke.extension;

import duke.task.Task;
import duke.view.AlertWindow;
import javafx.application.Platform;
import java.util.TimerTask;

/**
 * This is the notification alert that will pop up when the timer runs out.
 */
public class NotificationAlert extends TimerTask {
    public Task task;

    /**
     * Creates a new notification alert for the specified task.
     * @param task the task to create the notification alert for
     */
    public NotificationAlert(Task task) {
        this.task = task;
    }

    /**
     * Runs and triggers the notification alert when timer runs out.
     */
    public void run() {
        Platform.runLater(() -> {
            createAlertWindow();
            task.clearReminder();
        });
    }

    /**
     * Creates a new alert window.
     */
    private void createAlertWindow() {
        AlertWindow alertWindow = new AlertWindow();
        alertWindow.setTitle("Reminder");
        alertWindow.setContent(task.toString());
        alertWindow.addImage("/images/duke.png");
        alertWindow.style("/style/stylesheet.css", "alertPane");
        alertWindow.display();
    }

}
