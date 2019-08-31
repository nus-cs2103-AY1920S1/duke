package cs2103t.duke.task;

public enum TaskType {
    T, D, E, LIST, DELETE, DONE, EXIT, FIND;

    public static TaskType convertToTaskType(String typeString) {
        switch (typeString.toUpperCase()) {
        case "T": case "TODO": return T;
        case "D": case "DEADLINE": return D;
        case "E": case "EVENT": return E;
        case "LIST": return LIST;
        case "DELETE": return DELETE;
        case "DONE": return DONE;
        case "EXIT": case "BYE": return EXIT;
        case "FIND": return FIND;
        default: return null;
        }
    }
}
