package command;
import task.Task;
import task.TaskList;
import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;
import driver.Ui;

public abstract class Command {
    TaskList reference;
    Ui printer;


    public void executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        this.passToUI(this.formatOutput());
    }

    public abstract String formatOutput();

    public abstract void passToUI(String input);
}
