public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new PrintListCommand();
        } else if (fullCommand.startsWith("done ")) {
            return new MarkAsDoneCommand(Integer.parseInt(fullCommand.substring(5)) - 1);
        } else if (fullCommand.startsWith("delete ")) {
            return new DeleteTaskCommand(Integer.parseInt(fullCommand.substring(7)) - 1);
        } else {
            try {
                Task newTask;
                if (fullCommand.equals("deadline") || fullCommand.equals("event") || fullCommand.equals("todo")) {
                    throw new EmptyDescriptionException("☹ OOPS!!! The description of a "
                            + fullCommand + " cannot be empty.");
                } else if (fullCommand.startsWith("deadline")) {
                    String[] phrases = fullCommand.substring(9).split(" /by ");
                    newTask = new Deadline(phrases[0], phrases[1]);
                } else if (fullCommand.startsWith("event")) {
                    String[] phrases = fullCommand.substring(6).split(" /at ");
                    newTask = new Event(phrases[0], phrases[1]);
                } else if (fullCommand.startsWith("todo")) {
                    newTask = new Todo(fullCommand.substring(5));
                } else {
                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                return new AddTaskCommand(newTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException("Improper formatting of task description!");
            }
        }
    }
}
