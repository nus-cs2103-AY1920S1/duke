package command;
/**
 *
 */

import task.Task;
import task.TaskList;
import driver.Ui;

/**
 *
 */

public class AddCommand extends Command {
    String addedTask;
    Task added;

    /**
     *
     */

    public AddCommand(String x) {
        addedTask = x;
    }

    /**
     *
     */

    @Override
    public void executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        added = reference.addTasks(addedTask);
        this.passToUI(this.formatOutput());
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.addFormat(added,reference.getSize());
    }

    /**
     *
     */

    public void passToUI(String input) {
        printer.printAdd(input);
    }
}



