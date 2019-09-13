package duke.Command;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class DoneCommand {

    public DoneCommand() {

    }
    public void Done(TaskList tasks, String taskNumber) throws IOException {
        //iterate through the tasks Arraylist until task is found
        for (int i = 1; i <= tasks.get_TaskList().size(); i++) {
            if (i == (Integer.parseInt(taskNumber))) {
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [" + "\u2713" + "] " + tasks.get_TaskList().get(i - 1).get_Description());
                tasks.get_TaskList().get(i - 1).changeStatus(1);
                System.out.println("New status: " + tasks.get_TaskList().get(i - 1).get_Status());
                Storage.AutoSave(tasks, tasks.get_NoOfTasks());
            }
        }
    }
}
