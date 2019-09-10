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

    public String executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        return this.formatOutput();
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
