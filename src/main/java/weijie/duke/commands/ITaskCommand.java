package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.Optional;

public interface ITaskCommand {
    TaskResponse execute(String... args);

    Optional<UndoingAction> getUndoingAction();
}
