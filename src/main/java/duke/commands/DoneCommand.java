package duke.commands;

import duke.data.tasks.Task;

/**
 * Implements the done command.
 * @author Lim Yong Shen, Kevin
 */
public class DoneCommand extends Command {

    private Task taskToSetAsDone;
    private int taskNumber;
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n%s\n";
    public static final String COMMAND_WORD = "done";

    /**
     * Constructs a DoneCommand with the specified task number.
     * @param taskNumber The specified task number
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this DoneCommand and returns its CommandResult.
     * @return This DoneCommand's CommandResult.
     */
    @Override
    public CommandResult execute() {
        taskToSetAsDone = tasks.get(taskNumber - 1);
        taskToSetAsDone.setAsDone();
        return new CommandResult(String.format(SUCCESS_MESSAGE, taskToSetAsDone.toString()));
    }

}
