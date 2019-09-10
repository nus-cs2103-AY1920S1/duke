package command;
import task.Task;
import task.TaskList;
import driver.Ui;

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
    public String executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        done = reference.taskDone(doneIndex);
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {

        return TextFormatter.doneFormat(done);
    }

    /**
     *
     */

    public void passToUI(String input) {

        printer.printDone(input);
    }
}


