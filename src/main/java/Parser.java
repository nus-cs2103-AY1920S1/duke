import java.text.ParseException;

class Parser {

    static Command parse(String fullCommand) throws DukeException {
        String[] arr = fullCommand.split(" ", 2);
        String firstWord = arr[0];
        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "delete":
                int index = Integer.parseInt(arr[1]);
                return new DeleteCommand(index);
            case "done":
                int indexDone = Integer.parseInt(arr[1]);
                return new DoneCommand(indexDone);
            case "todo":
                String what = arr[1];
                if (what.isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(what);
                return new AddCommand(todo);
            case "deadline":
                String when = arr[1];
                if (when.isEmpty()) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] parts = when.split("/by");
                if (parts.length == 1) {
                    throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
                }
                String desc = parts[0];
                String time = parts[1];
                Deadline deadline = new Deadline(desc);
                try {
                    deadline.parseTime(time);
                } catch (ParseException ex) {
                    throw new DukeException(ex.getMessage());
                }
                return new AddCommand(deadline);
            case "event":
                String where = arr[1];
                if (where.isEmpty()) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }
                String[] partsE = where.split("/at");
                if (partsE.length == 1) {
                    throw new DukeException("OOPS!!! The time of an event cannot be empty.");
                }
                String descE = partsE[0];
                String timeE = partsE[1];
                Event event = new Event(descE);
                try {
                    event.parseTime(timeE);
                } catch (ParseException ex) {
                    throw new DukeException(ex.getMessage());
                }
                return new AddCommand(event);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
