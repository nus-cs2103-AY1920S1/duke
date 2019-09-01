/**
 * Parser class to parse user input into a Command.
 */
public class Parser {
    /**
     * Returns a Command based on user input.
     *
     * @param input user input.
     * @return Command based on user input.
     */
    public static Command parse(String input) {
        String[] inputArr = input.split(" ");
        Actions type;
        Command command = new WrongCommand(input);
        if (input.equals("bye")) {
            type = Actions.BYE;
            command = new ExitCommand(input);
        } else if (inputArr[0].equals("done")) {
            type = Actions.DONE;
            command = new UpdateCommand(input, type);
        } else if (input.equals("list")) {
            type = Actions.LIST;
            command = new UpdateCommand(input, type);
        } else if (inputArr[0].equals("todo")) {
            type = Actions.TODO;
            command = new AddCommand(input, type);

        } else if (inputArr[0].equals("deadline")) {
            type = Actions.DEADLINE;
            command = new AddCommand(input, type);

        } else if (inputArr[0].equals("event")) {
            type = Actions.EVENT;
            command = new AddCommand(input, type);

        } else if (inputArr[0].equals("delete")) {
            type = Actions.DELETE;
            command = new DeleteCommand(input);
        } else if (inputArr[0].equals("find")) {
            type = Actions.FIND;
            command = new FindCommand(input);
        } else {
            type = Actions.NONE;
        }
        return command;
    }
}
