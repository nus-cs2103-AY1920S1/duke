package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {
    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() > 0) {
            ui.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                StringBuilder sb = new StringBuilder();
                Task t = taskList.get(i);
                sb.append(i + 1).append(" ").append(t);
                ui.println(sb.toString());
            }
        } else {
            ui.println("There are no tasks in your list.");
        }
    }
}
