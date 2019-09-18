import java.util.ArrayList;

/**
 * Deals with making sense of user input.
 */
public class Parser {

    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Returns differnt types of Command objects depending on user input.
     * @param command represents the input by the user
     * @return Command object according to what the user inputs
     * @throws DukeException when the input is missing information or
     *                       in the wrong format
     */

    @SuppressWarnings("checkstyle:AvoidEscapedUnicodeCharacters")
    public static Command parse(String command) throws DukeException {
        String[] commandSplit = command.split(" ");
        String type = commandSplit[0];
        TaskType enumType;
        String[] timeSplit;

        switch (type) {
        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();
        }

        if (commandSplit.length == 1) {
            throw throwMissingDescException(type);
        }

        switch (type) {
        case "done":
            return new DoneCommand(commandSplit[1]);

        case "delete":
            return new DeleteCommand(commandSplit[1]);

        case "find":
            String[] wordsArray = keywordsToArray(commandSplit);
            return new KeyCommand(wordsArray);

        case "sort":
            return new SortCommand(commandSplit[1]);

        case "todo":
            enumType = TaskType.valueOf(type.toUpperCase());
            assert enumType == TaskType.TODO : "Enum is not right!";
            return new AddCommand(enumType, command.substring(5), "");

        case "deadline":
            enumType = TaskType.valueOf(type.toUpperCase());
            assert enumType == TaskType.DEADLINE : "Enum is not right!";
            timeSplit = splitCommandForTask(type, command);
            return new AddCommand(enumType, timeSplit[0].substring(9), timeSplit[1]);

        case "event":
            enumType = TaskType.valueOf(type.toUpperCase());
            assert enumType == TaskType.EVENT : "Enum is not right!";
            timeSplit = splitCommandForTask(type, command);
            return new AddCommand(enumType, timeSplit[0].substring(6), timeSplit[1]);

        case "fixed":
            enumType = TaskType.valueOf(type.toUpperCase());
            assert enumType == TaskType.FIXED : "Enum is not right!";
            timeSplit = splitCommandForTask(type, command);
            return new AddCommand(enumType, timeSplit[0].substring(6), timeSplit[1]);

        default:
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't"
                    + "know what that means :-(");
        }
    }

    private static String[] keywordsToArray(String[] keywords) {
        ArrayList<String> findWords = new ArrayList<>();
        for (int i = 1; i < keywords.length; i++) {
            findWords.add(keywords[i]);
        }
        String[] wordsArray = new String[findWords.size()];
        return findWords.toArray(wordsArray);
    }

    private static String[] splitCommandForTask(String type, String command) throws DukeException {
        String[] timeSplit;

        if (type.equals("event")) {
            timeSplit = command.split("/at ");
        } else if (type.equals("deadline")) {
            timeSplit = command.split("/by");
        } else {
            timeSplit = command.split("/needs");
        }

        if (timeSplit.length == 1) {
            throw new DukeException("\u2639 OOPS!!! Please enter a time / duration for your task.");
        }

        return timeSplit;
    }

    private static DukeException throwMissingDescException(String type) {
        DukeException toThrow = null;

        switch (type) {
        case "done":
            toThrow = new DukeException("\u2639 OOPS!!! Please indicate "
                    + "which task you have completed.");
            break;
        case "delete":
            toThrow = new DukeException("\u2639 OOPS!!! Please indicate which task "
                    + "you would like to delete.");
            break;
        case "find":
            toThrow = new DukeException("\u2639 OOPS!!! Please indicate the keyword "
                    + "you are looking for!");
            break;
        case "sort":
            toThrow = new DukeException("\u2639 OOPS!!! Please indicate how "
                    + "you want to sort the list!");
            break;
        case "todo":
        case "deadline":
        case "event":
            toThrow = new DukeException("\u2639 OOPS!!! The description of a "
                    + "task cannot be empty.");
            break;
        default:
             toThrow = new DukeException("\u2639 OOPS!!! I'm sorry, but I don't "
                     + "know what that means :-(");
        }

        return toThrow;
    }
}
