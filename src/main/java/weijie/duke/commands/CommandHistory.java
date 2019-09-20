package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private List<CommandState> commandStateHistory;
    private int presentState;
    private int maxIndex;

    public CommandHistory() {
        commandStateHistory = new ArrayList<>();
        presentState = 0;
    }

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
            return new TaskResponse("There is no command to undo!", new ArrayList<>());
        }

        presentState--;
        return commandStateHistory.get(presentState).undo();
    }

    TaskResponse redo() {
        if (presentState >= commandStateHistory.size() || presentState >= maxIndex) {
            return new TaskResponse("There is no command to redo!", new ArrayList<>());
        }

        TaskResponse taskResponse = commandStateHistory.get(presentState).redo();
        presentState++;
        return taskResponse;
    }
}
