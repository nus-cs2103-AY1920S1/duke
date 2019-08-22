public enum DukeCommand {
    LIST,
    DONE,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,
    BYE;

    public static DukeCommand parseCommand(String userInput) throws DukeException {
        if (userInput.matches("bye")) {
            return BYE;
        } else if (userInput.matches("list")) {
            return LIST;
        } else if (userInput.startsWith("done")) {
            return DONE;
        } else if (userInput.startsWith("delete")) {
            return DELETE;
        } else if (userInput.startsWith("todo")) {
            return TODO;
        } else if (userInput.startsWith("deadline")) {
            return DEADLINE;
        } else if (userInput.startsWith("event")) {
            return EVENT;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
