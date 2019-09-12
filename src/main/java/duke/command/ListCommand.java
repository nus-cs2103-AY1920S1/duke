package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        int totalNumber = tasks.numberOfTasks();
        for (int i = 0; i < totalNumber; i++) {
            int index = i + 1;
            sb.append(index + ". " + tasks.getList().get(i));
        }
        //test
        System.out.println(sb.toString());
        return sb.toString();
    }
}
