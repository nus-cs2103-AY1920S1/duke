import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of the user command.
 */

public class Parser {
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Method to parse input.
     *
     * @param input which is the input string
     * @return A Command
     */
    public static Command parse(String input) throws DukeException {
        assert input != null;
        String[] temp = input.split(" ");
        if (temp[0].equals("todo")) {
            if (temp.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String temp2 = "";
            for (int i = 1; i < temp.length; i++) {
                temp2 = temp2 + temp[i] + " ";
            }
            return new AddCommand(new Todo(temp2));
        } else if (temp[0].equals("event")) {
            if (temp.length <= 2) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String temp2 = "";
            String time = "";
            int index = 0;
            for (int i = 1; i < temp.length; i++) {
                if (temp[i].equals("/at")) {
                    index = i + 1;
                    break;
                } else {
                    temp2 = temp2 + temp[i] + " ";
                }
            }
            for (int j = index; j < temp.length; j++) {
                time = time + temp[j] + " ";
            }
            LocalDateTime dateTime = null;
            try {
                dateTime = LocalDateTime.parse(time.trim(), dtf);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter event in dd/mm/yyyy HHmm format");
            }
            return new AddCommand(new Event(temp2, dateTime));
        } else if (temp[0].equals("deadline")) {
            if (temp.length <= 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String temp2 = "";
            String time = "";
            int index = 0;
            for (int i = 1; i < temp.length; i++) {
                if (temp[i].equals("/by")) {
                    index = i + 1;
                    break;
                } else {
                    temp2 = temp2 + temp[i] + " ";
                }
            }
            for (int j = index; j < temp.length; j++) {
                time = time + temp[j] + " ";
            }
            LocalDateTime dateTime = null;
            try {
                dateTime = LocalDateTime.parse(time.trim(), dtf);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter deadline in dd/mm/yyyy HHmm format");
            }
            return new AddCommand(new Deadline(temp2, dateTime));
        } else if (temp[0].equals("bye")) {
            return new ExitCommand();
        } else if (temp[0].equals("done")) {
            if (temp.length > 2) {
                throw new DukeException("Invalid index given");
            }
            return new DoneCommand(Integer.parseInt(temp[1]) - 1);
        } else if (temp[0].equals("list")) {
            return new ListCommand();
        } else if (temp[0].equals("delete")) {
            if (temp.length > 2 || temp.length == 1) {
                throw new DukeException("Invalid index given");
            }
            return new DeleteCommand(Integer.parseInt(temp[1]) - 1);
        } else if (temp[0].equals("find")) {
            if (temp.length > 2 || temp.length == 1) {
                throw new DukeException("Invalid keyword given");
            }
            return new FindCommand(temp[1]);
        } else if (temp[0].equals("help")) {
            return new HelpCommand();
        }
        else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

