import java.io.FileNotFoundException;

public class DoneCommand extends Command {
    int taskNumToMark;

    public DoneCommand(int taskNumToMark) {
        this.taskNumToMark = taskNumToMark;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doneTask(taskNumToMark);
        storage.updateList(tasks.getList());
        return ui.print("Nice! I've marked this task as done: " + "\n" + tasks.getList().get(taskNumToMark - 1));
    }

}
