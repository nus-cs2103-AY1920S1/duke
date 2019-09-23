package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;
import static duke.ui.Message.EMPTY_LIST_MESSAGE;

public class ListCommand extends Command {
    public ListCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        int totalNumber = tasks.numberOfTasks();
        if (totalNumber == 0) {
            return EMPTY_LIST_MESSAGE;
        }
        for (int i = 0; i < totalNumber; i++) {
            int index = i + 1;
            sb.append(index + ". " + tasks.getList().get(i));
        }
        return sb.toString();
    }
}
