import java.util.Timer;
import java.util.TimerTask;

public class Exiter {

    /**
     * Shuts down the program after a specified duration (in milliseconds).
     */
    public void exitAfter(int ms) {
        assert ms >= 0 : "exitAfter was passed a negative argument";
        
        TimerTask exitTask = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };

        Timer timer = new Timer();
        timer.schedule(exitTask, ms);
    }
}