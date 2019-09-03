package command;
import task.Task;
import task.TaskList;
import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;
import driver.Ui;

public class DeleteCommand extends Command {
    int deletedIndex;
    Task removed;

    public DeleteCommand(int number) {
        deletedIndex = number;
    }

    @Override
    public void executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        removed = reference.deleteTask(deletedIndex);
        this.passToUI(this.formatOutput());
    }

    public String formatOutput() {
        return TextFormatter.deleteFormat(removed,reference.getSize());
    }

    public void passToUI(String input) {
        printer.printDelete(input);
    }
}
