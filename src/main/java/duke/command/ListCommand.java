package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.Storage;

public class ListCommand extends Command {
    public ListCommand(String[] description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String finalMsg = "";
        int totalNumber = tasks.numberOfTasks();
        for (int i = 0; i < totalNumber; i++) {
            int index = i + 1;
            finalMsg = finalMsg.concat(index + ". " + tasks.getList().get(i));
        }
        //test
        System.out.println(finalMsg);
        return finalMsg;
    }
}
