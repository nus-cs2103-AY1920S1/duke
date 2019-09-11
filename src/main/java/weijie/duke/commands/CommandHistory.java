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
        presentState = -1;
    }

    public void addCommand(ITaskCommand command) {
        System.out.println("before: " + commandStateHistory + "\nindex: " + presentState);
        command.getCommandState()
                .ifPresent(undo -> {
                    presentState++;
                    maxIndex = presentState;
                    if (presentState >= commandStateHistory.size()) {
                        commandStateHistory.add(undo);
                    } else {
                        commandStateHistory.set(presentState, undo);
                    }
                });
        System.out.println("after: " + commandStateHistory + "\nindex: " + presentState);
    }

    TaskResponse undo() {
        if (presentState < 0) {
            return new TaskResponse("There is no command to undo!", new ArrayList<>());
        }

        TaskResponse taskResponse = commandStateHistory.get(presentState).undo();
        presentState--;
        return taskResponse;
    }

    TaskResponse redo() {
        if (presentState >= commandStateHistory.size() - 1 || presentState >= maxIndex) {
            return new TaskResponse("There is no command to redo!", new ArrayList<>());
        }

        presentState++;
        return commandStateHistory.get(presentState).redo();
    }
}
