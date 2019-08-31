package cs2103t.duke.task;

/**
 * Represents the potential task type for Duke to do.
 * This includes creating tasks and other commands.
 * T represents todo task,
 * D represents deadline task,
 * E represents event task,
 * LIST represents list tasks,
 * DELETE represents delete tasks,
 * DONE represents mark tasks as done,
 * EXIT represents exit duke.
 */
public enum TaskType {
    T, D, E, LIST, DELETE, DONE, EXIT;

    /**
     * Converts command keyword to task type.
     * @param typeString command keyword.
     * @return TaskType of command.
     */
    public static TaskType convertToTaskType(String typeString) {
        switch (typeString.toUpperCase()) {
        case "T": case "TODO": return T;
        case "D": case "DEADLINE": return D;
        case "E": case "EVENT": return E;
        case "LIST": return LIST;
        case "DELETE": return DELETE;
        case "DONE": return DONE;
        case "EXIT": case "BYE": return EXIT;
        default: return null;
        }
    }
}
