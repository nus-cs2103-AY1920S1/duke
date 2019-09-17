package command;

import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super(command);
    }

    /**
     * Executes the change of tasks status to done.
     *
     * @param tasks is the taskList of tasks
     *
     * @param ui prints the return statements
     *
     * @param storage prints to the output
     *
     * @return the command to be printed
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            tasks.taskDone(val - 1);
            storage.updateFile(tasks);
            return ui.doneMessageFX(val - 1, tasks);
        } catch (Exception e) {
            return "Error, you have entered an invalid number";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
