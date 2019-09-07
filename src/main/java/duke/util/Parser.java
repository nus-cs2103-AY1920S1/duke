package duke.util;

public class Parser {
    /**
     * Returns a command according to user input
     * @param inputParts user input
     * @return Command object to be executed
     */
    public static Command parse(String[] inputParts) {
        switch (inputParts[0]) {
        case "bye":
            return new Command(inputParts, 0);
        case "list":
            return new Command(inputParts, 1);
        case "done":
            return new Command(inputParts, 2);
        case "delete":
            return new Command(inputParts, 3);
        case "find":
            return new Command(inputParts, 4);
        case "commands":
            return new Command(inputParts, -1);
        }
        String taskType = inputParts[0];
        assert taskType.equals("todo") ||
                taskType.equals("deadline") ||
                taskType.equals("event") : "Invalid Task";
        return new Command(inputParts, 5);
    }
}
