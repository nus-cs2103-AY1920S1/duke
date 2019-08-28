public class Parser {
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String command = inputArr[0];

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            try {
                return new DoneCommand(inputArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the task number you wish to\n     mark as done!");
            }
        case "delete":
            try {
                return new DeleteCommand(inputArr[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the task number you wish to\n     delete!");
            }
        case "todo":
            try {
                return new AddCommand(new Todo(inputArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        case "deadline":
            try {
                String[] detailsArr = inputArr[1].split(" /by ");
                return new AddCommand(new Deadline(detailsArr[0], detailsArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(
                        "Please follow the format:\n     deadline <description> /by <DD/MM/YYYY HHMM>");
            }
        case "event":
            try {
                String[] detailsArr = inputArr[1].split(" /at ");
                return new AddCommand(new Event(detailsArr[0], detailsArr[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(
                        "Please follow the format:\n     event <description> /at <DD/MM/YYYY HHMM>");
            }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
