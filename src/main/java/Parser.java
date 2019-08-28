public class Parser {
    /**
     * Parse user input into enums and separate the details accordingly
     * @param fullCommand
     * @return
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] strArr = fullCommand.split(" ", 2);
        if (strArr[0].equalsIgnoreCase("bye")) {
            return new Command(DukeEnum.BYE, "");
        } else if (strArr[0].contains("todo")) {
            return new Command(DukeEnum.TODO, strArr[1]);
        } else if (strArr[0].contains("deadline")) {
            return new Command(DukeEnum.DEADLINE, strArr[1]);
        } else if (strArr[0].contains("event")) {
            return new Command(DukeEnum.EVENT, strArr[1]);
        } else if (strArr[0].contains("done")) {
            return new Command(DukeEnum.DONE, strArr[1]);
        } else if (strArr[0].equalsIgnoreCase("list")) {
            return new Command(DukeEnum.LIST, "");
        } else if (strArr[0].contains("delete")) {
            return new Command(DukeEnum.DELETE, strArr[1]);
        } else {
            throw new DukeException("I don't know what that means :-( ");
        }
    }
}
