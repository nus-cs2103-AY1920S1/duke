package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;
import java.util.Optional;

public class InvalidCommand implements ITaskCommand {
    @Override
    public TaskResponse execute(String... args) {
        String response = "I don't even know what command you are.";
        return new TaskResponse(response, new ArrayList<>());
    }

    @Override
    public Optional<CommandState> getCommandState() {
        return Optional.empty();
    }
}
