package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int idx;
    private static final Commands DONE_COMMAND_TYPE = Commands.DONE;
    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:\n";

    /**
     * Constructor.
     * @param idx - Index of task to delete
     */
    public DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns command type of command.
     * @return Command type
     */
    public Commands getCommandType() {
        return DONE_COMMAND_TYPE;
    }

    /**
     * Execute done command on given task and save into taskList.
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.get(idx);
        taskList.done(this.idx);
        return this.getSuccessfulDoneMessage(task);
    }

    /**
     * Prints out message after successful marking of task.
     * @param task - duke.task.Task that has been successfully marked done
     */
    private String getSuccessfulDoneMessage(Task task) {
        return MARK_DONE_MESSAGE + task;
    }
}
