package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Exit Command. Command to say bye to user on CLI.
 */
public class ExitCommand extends Command {
    /**
     * Constructor. Sets isExit to true as it is the exit command.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Behaviour of exit command. Prints bye to user on CLI.
     * @param taskList TaskList of tasks.
     * @param ui Ui object for user interaction.
     * @param storage Storage object for data file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String indent = ui.getIndent();
        String message = indent + "Bye. Hope to see you again soon!";
        System.out.println(message);
    }
}
