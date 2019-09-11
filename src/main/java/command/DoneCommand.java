package command;
import task.Task;
import task.TaskList;

/**
 *
 */

public class DoneCommand extends Command {
    int doneIndex;
    Task done;

    /**
     *
     */

    public DoneCommand(int number) {
        doneIndex = number;
    }

    /**
     *
     */

    @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        done = reference.taskDone(doneIndex);
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {

        return TextFormatter.doneFormat(done);
    }


}


