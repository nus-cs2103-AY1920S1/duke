package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int idx;
    private boolean markAsUndone;
    private static final Commands DONE_COMMAND_TYPE = Commands.DONE;
    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:\n";
    private static final String MARK_UNDONE_MESSAGE = "I've unmarked this task: \n";

    /**
     * Constructor
     * @param idx - Index of task to delete
     * @param markAsUndone - if true, mark task as undone instead
     */
    public DoneCommand(int idx, boolean markAsUndone) {
        this.idx = idx;
        this.markAsUndone = markAsUndone;
    }

    /** Returns index of task to be marked **/
    public int getIndex() {
        return this.idx;
    }

    /**
     * @return Command type of command
     */
    public Commands getCommandType() {
        return DONE_COMMAND_TYPE;
    }

    /**
     * Execute done command on given task and save into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.get(idx);
        if (this.markAsUndone) {
            taskList.undone(idx);
        } else {
            taskList.done(this.idx);
        }
        return this.getSuccessfulDoneMessage(task);
    }

    /**
     * Prints out message after successful marking of task
     * @param task - duke.task.Task that has been successfully marked done
     */
    private String getSuccessfulDoneMessage(Task task) {
        String response;
        if(this.markAsUndone) {
            response = MARK_UNDONE_MESSAGE;
        } else {
            response = MARK_DONE_MESSAGE;
        }
        return response + task;
    }
}
