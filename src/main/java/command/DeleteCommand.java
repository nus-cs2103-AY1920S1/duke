package command;
import task.Task;
import task.TaskList;
import driver.Ui;

/**
 *
 */

public class DeleteCommand extends Command {
    int deletedIndex;
    Task removed;

    /**
     *
     */

    public DeleteCommand(int number) {
        deletedIndex = number;
    }

    /**
     *
     */

    @Override
    public String executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        removed = reference.deleteTask(deletedIndex);
        return this.formatOutput();
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.deleteFormat(removed,reference.getSize());
    }

    /**
     *
     */

    public void passToUI(String input) {
        printer.printDelete(input);
    }
}
