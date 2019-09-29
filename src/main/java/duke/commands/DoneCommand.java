package duke.commands;

import duke.data.tasks.Task;

/**
 * Implements the done command.
 * @author Lim Yong Shen, Kevin
 */
public class DoneCommand extends ListCommand {

    private Task taskToSetAsDone;
    private int taskNumber;
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Please enter a valid task number"
            + " to mark as done.\n";
    private static final String SUCCESS_MESSAGE = "Nice! I've marked this task as done:\n%s\n";
    private static final String TASK_ALREADY_DONE_MESSAGE = "That task is already done.\n";
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
        try {
            taskToSetAsDone = tasks.get(taskNumber - 1);
            assert taskToSetAsDone != null;
            if (taskToSetAsDone.isDone()) {
                return new CommandResult(TASK_ALREADY_DONE_MESSAGE);
            } else {
                taskToSetAsDone.setAsDone();
                return new CommandResult(String.format(SUCCESS_MESSAGE, taskToSetAsDone.toString()));
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

}
