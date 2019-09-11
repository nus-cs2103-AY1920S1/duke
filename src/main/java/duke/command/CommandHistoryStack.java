package duke.command;

import duke.task.TaskList;

import java.util.Stack;

/**
 * A Stack containing past commands - doesn't keep track of commands from previous runs.
 */
public class CommandHistoryStack {
    private Stack<TaskList> commandStack;
    private static final String UNDO_MESSAGE = "The last action was undone :)\n";

    public CommandHistoryStack() {
        this.commandStack = new Stack<>();
    }

    /**
     * Returns the most recent taskList - prior most recent modification.
     * @return most recent taskList
     */
    public TaskList pop() throws EmptyHistoryException {
        if (this.commandStack.isEmpty()) {
            throw new EmptyHistoryException();
        }
        return this.commandStack.pop();
    }

    /**
     * Adds in new taskList after modification is made.
     * @param command command that made modification
     * @param taskList existing taksList
     */
    public void update(Command command, TaskList taskList) {
        switch (command.getCommandType()) {
        case TODO:
        case DEADLINE:
        case EVENT:
        case DELETE:
        case DONE:
            this.commandStack.push(taskList.getCopyTaskList());
            break;
        default:

        }
    }

    /**
     * Returns message after successful undo-ing.
     * @param taskList - new tasklist
     * @return undo message
     */
    public String getUndoMessage(TaskList taskList) {
        return UNDO_MESSAGE + taskList.getPrintListMessage();
    }
}
