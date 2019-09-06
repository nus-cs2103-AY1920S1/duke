package duke.command;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Stack;

/**
 * A Stack containing past commands - doesn't keep track of commands from previous runs
 */
public class CommandHistoryStack {
    private static Stack<Command> commandStack;
    private static final boolean MARK_AS_UNDONE = true;

    public CommandHistoryStack() {
        commandStack = new Stack<>();
    }

    /**
     * @return most recent command
     */
    public Command pop() throws EmptyHistoryException {
        if(commandStack.isEmpty()) { throw new EmptyHistoryException(); }
        return commandStack.pop();
    }

    public void update(Command command, TaskList taskList) {
        switch(command.getCommandType()) {
        case DELETE:
            commandStack.push(retrieveAddCommand(command, taskList));
            break;
        case DONE:
            commandStack.push(retrieveDeleteCommand(command, taskList));
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            commandStack.push(new DeleteCommand(taskList.size()));
            break;
        }
    }

    private Command retrieveAddCommand(Command command, TaskList taskList) {
        DeleteCommand deleteCommand = (DeleteCommand) command;
        Task task = taskList.get(deleteCommand.getIndex());
        return new AddCommand(task);
    }

    private Command retrieveDeleteCommand(Command command, TaskList taskList) {
        DoneCommand doneCommand = (DoneCommand) command;
        int idx = doneCommand.getIndex();
        return new DoneCommand(idx, MARK_AS_UNDONE);
    }
}
