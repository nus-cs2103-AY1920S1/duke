/**
 * Deals with making sense of the user command.
 */
public class Parser {

    public static Command parse(String fullCommand) {
        String[] currArray = fullCommand.split("\\s+", 2);
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (currArray[0].equals("done")) {
            return new DoneCommand(currArray[1]);
        } else if (currArray[0].equals("delete")) {
            return new DeleteCommand(currArray[1]);
        } else if (currArray[0].equals("find")) {
            return new FindCommand(currArray[1]);
        } else {
            return new AddCommand(currArray);
        }
    }

}