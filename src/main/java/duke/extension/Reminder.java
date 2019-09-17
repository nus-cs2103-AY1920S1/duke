package duke.extension;

import duke.formatter.DateFormatter;
import duke.task.Task;

import java.util.Date;
import java.util.Timer;

public class Reminder {
    Timer timer;
    Date dateToRemind;

    public Reminder(Task task, Date dateToRemind) {
        this.dateToRemind = dateToRemind;
        this.timer = new Timer();
        this.timer.schedule(new NotificationAlert(task), dateToRemind);
    }

    public Date getDate() {
        return dateToRemind;
    }

    @Override
    public String toString() {
        return "⏰ " + dateToRemind.toString() + " ⏰";
    }

}
