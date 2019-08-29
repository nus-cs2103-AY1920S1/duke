package duke.commands;

import duke.data.tasks.Task;

/**
 * Implements the delete command.
 * @author Lim Yong Shen, Kevin
 */
public class DeleteCommand extends Command {

    private int taskNumber;
    private static final String SUCCESS_MESSAGE = "Noted. I've removed this task:\n"
            + "%s\nNow you have %d task(s) in the list.\n";
    public static final String COMMAND_WORD = "delete";

    /**
     * Constructs a DeleteCommand with the specified task number.
     * @param taskNumber The specified task number.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this DeadlineCommand and returns its CommandResult.
     * @return This DeadlineCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        Task task = tasks.remove(taskNumber - 1);
        return new CommandResult(String.format(SUCCESS_MESSAGE, task.toString(), tasks.size()));
    }

}
