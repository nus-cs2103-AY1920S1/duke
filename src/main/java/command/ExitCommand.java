package command;

import util.Storage;
import util.TodoList;

import java.util.Timer;
import java.util.TimerTask;

public class ExitCommand extends command.Command {
    private static final String FAREWELL = "Bye. Hope to see you again soon!";

    private void exit() {
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
    public String run(TodoList tasks, Storage storage) {
        exit();
        return FAREWELL;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
