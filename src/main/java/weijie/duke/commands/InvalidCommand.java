package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

import java.util.ArrayList;

public class InvalidCommand implements ITaskCommand {
    @Override
    public TaskResponse execute(String... args) {
        String response = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        return new TaskResponse(response, new ArrayList<>());
    }
}
