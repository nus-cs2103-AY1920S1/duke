package duke.util;

/**
 * Class for Parser object.
 */
public class Parser {
    private static final int BYE = 0;
    private static final int LIST = 1;
    private static final int DONE = 2;
    private static final int DELETE_TASK = 3;
    private static final int FIND = 4;
    private static final int TASK = 5;
    private static final int NOTE = 6;
    private static final int DELETE_NOTE = 7;
    private static final int COMMANDS = -1;

    /**
     * Returns a command according to user input.
     *
     * @param inputParts user input
     * @return Command object to be executed
     */
    public static Command parse(String[] inputParts) {
        switch (inputParts[0]) {
        case "bye":
            return new Command(inputParts, BYE);
        case "list":
            return new Command(inputParts, LIST);
        case "done":
            return new Command(inputParts, DONE);
        case "delete":
            if (isTaskDeletion(inputParts)) {
                return new Command(inputParts, DELETE_TASK);
            } else if (isNoteDeletion(inputParts)) {
                return new Command(inputParts, DELETE_NOTE);
            } else {
                return new Command(inputParts, TASK); //create invalid command next time
            }
        case "find":
            return new Command(inputParts, FIND);
        case "commands":
            return new Command(inputParts, COMMANDS);
        case "note":
            return new Command(inputParts, NOTE);
        default:
            return new Command(inputParts, TASK);
        }
    }

    private static boolean isTaskDeletion(String[] inputParts) {
        return inputParts[1].split(" ", 2)[0].equals("task");
    }

    private static boolean isNoteDeletion(String[] inputParts) {
        return inputParts[1].split(" ", 2)[0].equals("note");
    }
}
