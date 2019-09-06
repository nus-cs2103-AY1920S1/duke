package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String str;

    public FindCommand(String str) {
        this.str = str;
    }

    /**
     * Tells whether the command is exit command.
     *
     * @return true when it is.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the duke.command according to the command type.
     *
     * @param taskList The list of tasks maintained in Duke
     * @param ui       Ui module
     * @param storage  in charge of loading and saving the tasks
     * @throws DukeException when the command is invalid
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> findList = new ArrayList<>();
        for (Task task : taskList.getList()) {
            if (task.getDescription().contains(str)) {
                findList.add(task);
            }
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            sb.append("\n" + (i + 1) + "." + taskList.getTask(i + 1));
        }
        ui.print(sb.toString());
    }
}
