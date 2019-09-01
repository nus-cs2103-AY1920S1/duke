
import java.io.FileNotFoundException;

public class ExitCommand extends Command {
    /**
     * This method is used to exit from application.
     *
     * @return duke response after exit
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //tasks.addTask(taskToAdd);
        storage.updateList(tasks.getList());
        return ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
