package command;

/**
 * CommandType Enum.
 *
 * <p>Represents the type of a Command.
 *
 * @author Marcus Ong
 */
public enum CommandType {
    ADD_ALIAS("addAlias", "Add Alias"),
    DELETE_ALIAS("deleteAlias", "Delete Alias"),
    LIST_ALIASES("listAliases", "List Aliases"),
    ADD_DEADLINE("addDeadline", "Add Deadline"),
    ADD_EVENT("addEvent", "Add Event"),
    ADD_TODO("addTodo", "Add Todo"),
    DELETE_TASK("deleteTask", "Delete Task"),
    DONE_TASK("doneTask", "Done Task"),
    EXIT("exit", "Exit"),
    FIND_TASK("findTask", "Find Task"),
    LIST_TASKS("listTasks", "List Tasks"),;

    protected final String id;
    protected final String prettyId;

    CommandType(String id, String prettyId) {
        this.id = id;
        this.prettyId = prettyId;
    }

    public String getId() {
        return id;
    }

    public String getPrettyId() {
        return prettyId;
    }

    @Override
    public String toString() {
        return getId() + ": " + getPrettyId();
    }
}
