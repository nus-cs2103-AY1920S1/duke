public class Parser {

    public Command parse(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return new AddCommand(AddCommand.AddType.TODO, input);
        } else if (input.startsWith("deadline")) {
            return new AddCommand(AddCommand.AddType.DEADLINE, input);
        } else if (input.startsWith("event")) {
            return new AddCommand(AddCommand.AddType.EVENT, input);
        } else if ("list".equals(input)) {
            return new QueryCommand(QueryCommand.QueryType.LIST_ALL, "");
        } else if (input.startsWith("done")) {
            return new UpdateCommand(UpdateCommand.UpdateType.DONE, input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(DeleteCommand.DeleteType.INDEX, input);
        } else if ("bye".equals(input)) {
            return new ExitCommand();
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
