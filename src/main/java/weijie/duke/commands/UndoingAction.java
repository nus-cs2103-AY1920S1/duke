package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

public interface UndoingAction {
    TaskResponse undo();
}
