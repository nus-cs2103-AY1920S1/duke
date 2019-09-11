public class Parser {
    public static Command parse(String input) throws DukeException, IllegalArgumentException {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("bye")) {
            return new ExitCommand();
        } else if (tokens[0].equals("list")) {
            return new ListCommand();
        } else if (tokens[0].equals("done")) {
            return DoneCommand.createDoneIfValid(tokens);
        } else if (tokens[0].equals("delete")) {
            return DeleteCommand.createDeleteIfValid(tokens);
        } else {
            return Command.createAddCommandIfValid(tokens);
        }
    }
}






