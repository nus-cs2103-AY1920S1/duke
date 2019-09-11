package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private List<UndoingAction> undoHistory;
    private int presentState;

    public CommandHistory() {
        undoHistory = new ArrayList<>();
        presentState = -1;
    }

    public void addCommand(ITaskCommand command) {
        command.getUndoingAction()
                .ifPresent(undo -> {
                    presentState++;
                    if (presentState >= undoHistory.size()) {
                        undoHistory.add(undo);
                    } else {
                        undoHistory.set(presentState, undo);
                    }
                });
    }

    TaskResponse undo() {
        if (presentState < 0) {
            return new TaskResponse("There is no command to undo!", new ArrayList<>());
        }

        TaskResponse taskResponse = undoHistory.get(presentState).undo();
        presentState--;
        return taskResponse;
    }
}
