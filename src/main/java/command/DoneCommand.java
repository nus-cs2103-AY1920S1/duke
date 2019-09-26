package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        this.command = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        assert (!command.isEmpty()) : "Input command cannot be empty";
        if (command.substring(4).isEmpty()) {
            Ui.doneErrorMsg();
        } else {
            try {
                String desc = command.substring(5);
                int index = Integer.parseInt(desc);
                if (index < 0 || index >= taskList.getTasks().size()) {
                    Ui.outOfBoundMsg();
                }
                taskList.getTask(index).markAsDone();
                Ui.doneMsg();
            } catch (NumberFormatException e) {
                Ui.invalidNumMsg();
            }
        }

    }
}
