import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * deals with making sense of the user command
 * */
public class Parser {
    protected String command;
    public Command parse(String fullcommand) throws DukeException{
        try {
            if (fullcommand.equals("list")) { //LIST
                return new ListCommand();
            } else if (fullcommand.startsWith("done")) { //DONE
                command = fullcommand.replaceFirst("done", "").trim();
                return new UpdateCommand(command);
            } else if (fullcommand.startsWith("delete")) { //DELETE
                command = fullcommand.replaceFirst("delete", "").trim();
                return new DeleteCommand(command);
            } else if (fullcommand.startsWith("find")) { //FIND
                command = fullcommand.replaceFirst("find", "").trim();
                return new FindCommand(command);
            } else if (fullcommand.startsWith("todo")) { //ADD: TODO
                command = fullcommand.replaceFirst("todo", "").trim();
                return new AddCommand("T", command);
            } else if (fullcommand.startsWith("deadline")) { //ADD: DEADLINE
                command = fullcommand.replaceFirst("deadline", "").trim();
                return new AddCommand("D", command);
            } else if (fullcommand.startsWith("event")) { //ADD: EVENT
                command = fullcommand.replaceFirst("event", "").trim();
                return new AddCommand("E", command);
            } else if (fullcommand.equals("bye")) {
                return new ExitCommand();
            } else {
                throw new DukeException("       OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    // check if task command is valid
    public void checkTask(String type, String command) throws DukeException {
        if (type.equals("T")) {
            if (command.isEmpty()) {
                throw new DukeException("       OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (type.equals("D")) {
            String[] arr = command.split("/by");
            if (arr[0].trim().isEmpty()) {
                throw new DukeException("       OOPS!!! The description of a deadline cannot be empty.");
            } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                throw new DukeException("       OOPS!!! The deadline must have a specified date/time.");
            }
        } else if (type.equals("E")) {
            String[] arr = command.split("/at");
            if (arr[0].trim().isEmpty()) {
                throw new DukeException("       OOPS!!! The description of a event cannot be empty.");
            } else if (arr.length < 2 || arr[1].trim().isEmpty()) {
                throw new DukeException("       OOPS!!! The event must have a specified date/time.");
            }
        }
    }

    // check if command is valid number
    public boolean validNumber(String command, int listSize) throws DukeException {
        try {
            if (command.isEmpty()) {
                throw new DukeException("       OOPS!!! Command cannot be empty! Please try again!");
            }

            if (!command.matches("\\d+")) {
                throw new DukeException("       OOPS!!! Task id has to be numeric type!");
            }

            int pos = Integer.parseInt(command);
            if (pos > listSize || pos <= 0) {
                throw new DukeException("       OOPS!!! Task id not within range of total number of tasks!");
            }

        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        return true;
    }

    // format date and time
    public String datetimeformat(String input) throws DukeException {
        try {
            System.out.println(input);
            LocalDateTime datetime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")); //convert to ISO_LOCAL_DATE_TIME
            DateTimeFormatter format = DateTimeFormatter.ofPattern(" MMMM yyyy, ha");
            return getSuffix(datetime.getDayOfMonth()).concat(datetime.format(format));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format: dd/mm/yyyy HHmm only!");
        }
    }

    // get day suffix for date formatter
    public String getSuffix(int day) {
        switch (day) {
            case 1:
            case 21:
            case 31:
                return day + "st";
            case 2:
            case 22:
                return day + "nd";
            case 3:
            case 23:
                return day + "rd";
            default:
                return day + "th";
        }
    }

}
