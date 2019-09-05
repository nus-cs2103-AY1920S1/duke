import java.util.Timer;
import java.util.TimerTask;

public class Exiter {

    public void exitAfter(int ms) {
        TimerTask exitTask = new TimerTask() {
            public void run() {
                System.exit(0);
            }
        };

        Timer timer = new Timer();
        timer.schedule(exitTask, ms);
    }
}