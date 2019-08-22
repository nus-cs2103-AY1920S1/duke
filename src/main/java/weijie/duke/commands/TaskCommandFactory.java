package weijie.duke.commands;


import java.util.Map;

public class TaskCommandFactory {
    private final Map<String, ITaskCommand> commandMap;

    public TaskCommandFactory(Map<String, ITaskCommand> commandMap) {
        this.commandMap = commandMap;
    }

    public ITaskCommand tryMakeCommand(String command) {
        return commandMap.getOrDefault(command, new InvalidCommand());

    }
}
