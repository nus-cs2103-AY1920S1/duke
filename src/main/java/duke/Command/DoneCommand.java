package duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class DoneCommand {

    public DoneCommand() {

    }
    public String Done(TaskList tasks, Storage storage, String taskNumber, Ui ui) throws IOException {
        String output = "";
        //iterate through the tasks Arraylist until task is found
        for (int i = 1; i <= tasks.get_TaskList().size(); i++) {
            if (i == (Integer.parseInt(taskNumber))) {
                tasks.get_TaskList().get(i - 1).changeStatus(1);
                storage.AutoSave(tasks, tasks.get_NoOfTasks());
                output = ui.print_done(tasks, i);
            }
        }
        return output;
    }
}
