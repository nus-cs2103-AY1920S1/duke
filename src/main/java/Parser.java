public class Parser {
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
        } else {
            type = Actions.NONE;
        }
        return command;
    }
//method takes input, returns type of output --> compare to java enums
}
