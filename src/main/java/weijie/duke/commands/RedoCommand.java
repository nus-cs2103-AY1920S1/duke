package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.Optional;

public class RedoCommand implements ITaskCommand {
    private CommandHistory history;

    RedoCommand(CommandHistory history) {
        this.history = history;
    }

    @Override
    public TaskResponse execute(String... args) {
        return history.redo();
    }

    @Override
    public Optional<CommandState> getCommandState() {
        return Optional.empty();
    }
}
