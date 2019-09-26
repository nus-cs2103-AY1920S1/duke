package command;

import task.TaskList;
import util.Storage;
import util.Ui;

public class DoneCommand extends Command {

    public DoneCommand(String command) {
        this.inputCommand = command;
    }

    @Override
    public void executeCommand(TaskList taskList, Storage storage) {
        assert (!inputCommand.isEmpty()) : "Input inputCommand cannot be empty";
        if (inputCommand.substring(4).isEmpty()) {
            Ui.doneErrorMsg();
        } else {
            try {
                String desc = inputCommand.substring(5);
                int index = Integer.parseInt(desc);
                if (index < 0 || index >= taskList.getTasks().size()) {
                    Ui.outOfBoundMsg();
                    return;
                }
                taskList.getTask(index - 1).markAsDone();
                Ui.doneMsg();
                System.out.println(taskList.getTask(index - 1).toString());
            } catch (NumberFormatException e) {
                Ui.invalidNumMsg();
            }
        }

    }
}
