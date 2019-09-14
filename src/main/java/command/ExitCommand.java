package command;

import task.Ui;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The ExitCommand class defines the behaviour of an exit command.
 * 
 * @author Joel Loong
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    /**
     * Closes the GUI after 1000 milliseconds.
     */
    public void exit() {
        Timer timer = new Timer();
        final long delay = 1000L;
        timer.schedule((new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }), delay);
    }

    @Override
    public String execute() {
        exit();
        return Ui.endOfInteractions();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}