package q.user;

import javafx.application.Platform;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles the exiting of the program.
 */
public class TimedExit {
    Timer timer = new Timer();
    TimerTask exitApp = new TimerTask() {
        public void run() {
            Platform.exit();
            System.exit(0);
        }
    };

    /**
     * Exits the program in 3 seconds.
     */
    public TimedExit() {
        timer.schedule(exitApp, new Date(System.currentTimeMillis() + 3 * 1000));
    }
}
