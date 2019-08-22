package weijie.duke.commands;

import weijie.duke.responses.TaskResponse;

public interface ITaskCommand {
    TaskResponse execute(String... args);
}
