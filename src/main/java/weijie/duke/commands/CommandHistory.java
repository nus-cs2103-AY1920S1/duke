package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * Handles keeping track of the command history of executed commands. Commands that have been executed can be added
 * to an instance of this class via {@code addCommand}. Once added, the command will be tracked, and the most recently
 * added command can be undone via {@code undo}. Calling {@code undo} subsequent times will undo the next most recent
 * command that has not yet been undone. A command that has been undone can always be redone by calling {@code redo}
 * unless another command has been executed, in which case the undone commands will be wiped and replaced with a new
 * history branch.
 */
public class CommandHistory {

    private List<CommandState> commandStateHistory;
    private int presentState;
    private int maxIndex;

    public CommandHistory() {
        commandStateHistory = new ArrayList<>();
        presentState = 0;
    }

    /**
     * <p>
     *     Adds a command to the command history, allowing the command to be undone and redone at a later point.
     * </p>
     * @param command The command to be added to the command history.
     */
    public void addCommand(ITaskCommand command) {
        command.getCommandState()
                .ifPresent(undo -> {
                    if (presentState >= commandStateHistory.size()) {
                        commandStateHistory.add(undo);
                    } else {
                        commandStateHistory.set(presentState, undo);
                    }

                    presentState++;
                    maxIndex = presentState;
                });
    }

    TaskResponse undo() {
        if (presentState <= 0) {
            return new TaskResponse("There is no command to undo.", new ArrayList<>());
        }

        presentState--;
        return commandStateHistory.get(presentState).undo();
    }

    TaskResponse redo() {
        if (presentState >= commandStateHistory.size() || presentState >= maxIndex) {
            return new TaskResponse("There is no command to redo.", new ArrayList<>());
        }

        TaskResponse taskResponse = commandStateHistory.get(presentState).redo();
        presentState++;
        return taskResponse;
    }
}
