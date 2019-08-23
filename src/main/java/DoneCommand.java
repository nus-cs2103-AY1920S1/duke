import java.util.ArrayList;
import java.util.Scanner;

public class DoneCommand extends Command {

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        int taskDoneIndex = sc.nextInt() - 1;
        taskLst.get(taskDoneIndex).markAsDone();
        System.out.printf("     Nice! I've marked this task as done:\n       %s\n"
                , taskLst.get(taskDoneIndex));
    }

}
