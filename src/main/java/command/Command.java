package command;
import task.TaskList;
import driver.Ui;

/**
 *
 */

public abstract class Command {
    TaskList reference;
    Ui printer;


    /**
     *
     */

    public void executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        this.passToUI(this.formatOutput());
    }

    /**
     *
     */

    public abstract String formatOutput();

    /**
     *
     */

    public abstract void passToUI(String input);
}
