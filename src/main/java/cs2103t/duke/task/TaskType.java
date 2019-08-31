package cs2103t.duke.task;

public enum TaskType {
    T, D, E, LIST, DELETE, DONE, EXIT;

    public static TaskType convertToTaskType(String typeString) {
        TaskType type;
        switch (typeString.toUpperCase()) {
        case "T":
            //Fallthrough
        case "TODO":
            type = T;
            break;
        case "D":
            //Fallthrough
        case "DEADLINE":
            type = D;
            break;
        case "E":
            //Fallthrough
        case "EVENT":
            type = E;
            break;
        case "LIST":
            type = LIST;
            break;
        case "DELETE":
            type = DELETE;
            break;
        case "DONE":
            type = DONE;
            break;
        case "EXIT":
            //Fallthrough
        case "BYE":
            type = EXIT;
            break;
        default:
            type = null;
            break;
        }
        return type;
    }
}
