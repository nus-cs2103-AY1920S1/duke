package duke.command;

import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            response = ui.NO_TASKS_RESPONSE;
        } else {
            StringBuilder sb = new StringBuilder(ui.LISTING_RESPONSE);
            for (int i = 0; i < tasks.size(); i++) {
                int k = i + 1;
                Task task = tasks.get(i);
                if (i > 0) {
                    sb.append("\n");
                }
                sb.append(k);
                sb.append(". ");
                sb.append(task);
            }
            response = sb.toString();
        }
    }
}
