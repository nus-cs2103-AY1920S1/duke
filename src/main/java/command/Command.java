package command;
import main.TaskList;
import main.Ui;
import main.Storage;
import task.InsufficientTaskArgumentException;
import task.InvalidTaskException;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InsufficientTaskArgumentException, InvalidTaskException;
    public boolean isExit();
    public String toString();
}
