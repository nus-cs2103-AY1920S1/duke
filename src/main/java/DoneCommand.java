import java.io.IOException;

public class DoneCommand extends Command {
    private int doIndex;

    public DoneCommand(int doIndex) {
        this.doIndex = doIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task chosen_Task = tasks.getTask(doIndex - 1);
        chosen_Task.markAsDone();
        try {
            storage.updateTaskInFile(doIndex);
            System.out.println("Nice! I've marked this task as done:\n  " +
                    chosen_Task.toString());
        } catch (IOException ex) {
            System.out.println("Can't update task in the file");
        }
    }
}
