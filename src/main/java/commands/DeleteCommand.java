package commands;

import java.util.ArrayList;
import java.util.Scanner;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import tasks.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        isExit = false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Scanner sc = new Scanner(fullCommand);
        sc.next();
        ArrayList<Task> taskLst = tasks.getTaskLst();
        int delTaskIndex = sc.nextInt() - 1;
        Task deletedTask = taskLst.remove(delTaskIndex);
        System.out.printf("     Noted. I've removed this task:\n" +
                        "       %s\n     Now you have %d tasks in the list.\n"
                , deletedTask, taskLst.size());
    }

}
