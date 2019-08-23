import java.util.Arrays;

public class Parser {
    /**
     * Handles parsing of user input.
     * @param input
     * @return a new Task represented by the input, null otherwise
     * @throws IllegalInstructionException
     */
    public static Command parse(String input)
            throws IllegalInstructionException {
        String[] strings = input.split("\\s+");
        String command = strings[0];

        switch (command) {
        case "list":
            return new ListCommand(input);

        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input);

        case "done":
            try {
                int index = Integer.parseInt(strings[1]);
                return new DoneCommand(command, index);
            } catch (NumberFormatException e) {
                throw new IllegalInstructionException("Please enter a number after \"done\"!");
            }

        case "bye":
            return new AddCommand(command);

        default:
            throw new IllegalInstructionException("Sorry! I don't know what that means.");
        }

    }
}
