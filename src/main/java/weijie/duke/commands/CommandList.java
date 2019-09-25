package weijie.duke.commands;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public enum CommandList {
    LIST_COMMAND("list", ListCommand.class),
    ADD_TODO_COMMAND("todo", AddTodoCommand.class),
    ADD_DEADLINE_COMMAND("deadline", AddDeadlineCommand.class),
    ADD_EVENT_COMMAND("event", AddEventCommand.class),
    DONE_COMMAND("done", DoneCommand.class),
    DELETE_COMMAND("delete", DeleteCommand.class),
    FIND_COMMAND("find", FindCommand.class),
    UNDO_COMMAND("undo", UndoCommand.class),
    REDO_COMMAND("redo", RedoCommand.class),
    SNAP_COMMAND("snap", SnapCommand.class),
    INVALID_COMMAND(null, InvalidCommand.class);


    private static Map<String, Class<? extends ITaskCommand>> commandMap;
    private String commandString;
    private Class<? extends ITaskCommand> commandClass;

    static {
        commandMap = new HashMap<>();
        for (CommandList command : values()) {
            commandMap.put(command.getCommandString(), command.getCommandClass());
        }
    }

    CommandList(String command, Class<? extends ITaskCommand> commandClass) {
        this.commandString = command;
        this.commandClass = commandClass;
    }

    public static Map<String, Class<? extends ITaskCommand>> getCommandMap() {
        return commandMap;
    }

    public String getCommandString() {
        return commandString;
    }

    public Class<? extends ITaskCommand> getCommandClass() {
        return commandClass;
    }
}
