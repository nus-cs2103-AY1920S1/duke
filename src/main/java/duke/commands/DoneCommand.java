package duke.commands;

import duke.data.tasks.Task;

/**
 * Implements the done command.
 * @author Lim Yong Shen, Kevin
 */
public class DoneCommand extends ListCommand {

    private Task taskToSetAsDone;
    private int taskNumber;
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n%s\n";
    public static final String COMMAND_WORD = "done";

    /**
     * Constructs a done command with the specified task number.
     * @param taskNumber The specified task number.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes this done command and returns its command result.
     * @return This done command's command result.
     */
    @Override
    public CommandResult execute() {
        taskToSetAsDone = tasks.get(taskNumber - 1);
        taskToSetAsDone.setAsDone();
        return new CommandResult(String.format(SUCCESS_MESSAGE, taskToSetAsDone.toString()));
    }

}
