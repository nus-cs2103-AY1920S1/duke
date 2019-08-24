package duke.command;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class FindCommand extends TextBasedCommand {
    public static final String COMMAND = "find";

    /**
     * Find command for finding a particular string in tasks in the task list.
     * @param line entire line of input by user.
     * @throws DukeException generic exception with error message
     */
    public FindCommand(String line) throws DukeException {
        super(line, COMMAND);
    }

    @Override
    public void execute(Duke duke, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.println("____________________________________________________________\n"
                + "Here are the matching tasks in your list:\n");
        //I'm assuming index matches the index in the list
        for (int i = 0; i < taskList.size(); i++) {
            String taskDetails = taskList.get(i).toString();
            if (taskDetails.contains(remainingLine)) {
                ui.println((i + 1) + "." + taskDetails);
            }
        }
        ui.println("____________________________________________________________");
    }
}
