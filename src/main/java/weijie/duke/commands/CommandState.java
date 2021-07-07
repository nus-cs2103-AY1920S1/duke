package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

public interface CommandState {
    TaskResponse undo();

    TaskResponse redo();
}
