import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskLst.size(); i++) {
            System.out.printf("     %d.%s\n",
                    i + 1, taskLst.get(i));
        }
    }

}
