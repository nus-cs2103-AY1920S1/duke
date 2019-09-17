import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {
    static String EXIT_COMMAND = "bye";
    static String LIST_COMMAND = "list";
    static String DONE_COMMAND = "done ";
    static String FIND_COMMAND = "find ";
    static String DELETE_COMMAND = "delete ";
    static String ADD_TODO_COMMAND = "todo ";
    static String ADD_DEADLINE_COMMAND = "deadline ";
    static String ADD_EVENT_COMMAND = "event ";
    private static String IDENTIFIER = "UnIqUE_kEy_4324345";

    /**
     * Parses all user inputs.
     *
     * @param command user input
     * @return command to be executed
     * @throws DukeException If invalid command
     */
    public static Command parse(String command) throws DukeException {
        if (isEditTaskCommand(command)) {
            return getEditTaskCommand(command);
        } else if (isAddTaskCommand(command)) {
            return getAddTaskCommand(command);
        } else if (isFunctionalCommand(command)) {
            return getFunctionalCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isAddTaskCommand(String command) {
        if (command.startsWith(ADD_TODO_COMMAND)
                || command.startsWith(ADD_DEADLINE_COMMAND)
                || command.startsWith(ADD_EVENT_COMMAND)) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean isEditTaskCommand(String command) {
        if (command.startsWith(DELETE_COMMAND)
                || command.startsWith(DONE_COMMAND)) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean isFunctionalCommand(String command) {
        if (command.equals(EXIT_COMMAND)
                || command.equals(LIST_COMMAND)
                || command.startsWith(FIND_COMMAND)) {
            return true;
        } else {
            return false;
        }
    }

    private static Command getAddTaskCommand(String command) throws DukeException {
        if (command.startsWith(ADD_TODO_COMMAND)) {
            return new AddCommand(new Todo(command));
        } else if (command.startsWith(ADD_DEADLINE_COMMAND)) {
            if (!command.contains("/by ")) {
                throw new DukeException("☹ OOPS!!! Wrong format ! Please follow :\n deadline return book /by 15/09/19");
            } else {
                return new AddCommand(new Deadline(formatTime(command)));
            }
        } else if (command.startsWith(ADD_EVENT_COMMAND)) {
            if (!command.contains("/at ")) {
                throw new DukeException("☹ OOPS!!! Wrong format ! "
                        + "Please follow :\n event project meeting /at 15/09/19");
            } else {
                return new AddCommand(new Event(formatTime(command)));
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private static Command getEditTaskCommand(String command) throws DukeException {
        if (command.startsWith(DONE_COMMAND)) {
            try {
                int id = Integer.parseInt(command.substring(5));
                return new DoneCommand(id);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! There is no such Task !");
            }
        } else if (command.startsWith(DELETE_COMMAND)) {
            try {
                int id = Integer.parseInt(command.substring(7));
                return new DeleteCommand(id);
            } catch  (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! There is no such Task !");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    private static Command getFunctionalCommand(String command) throws DukeException {
        if (command.equals(EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (command.equals(LIST_COMMAND)) {
            return new ListCommand();
        } else  if (command.startsWith(FIND_COMMAND)) {
            return new FindCommand(command.substring(5));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * To format time of user input.
     *
     * @param input user input
     * @return formatted time string
     * @throws DukeException if invalid time format
     */
    private static String formatTime(String input) throws DukeException {
        List<String> formatStrings = Arrays.asList("d/M/y HHmm", "d/M/y", "d/M");
        List<String> outputFormats = Arrays.asList("MMMMM dd, yyyy, h:mm a", "MMMMM dd, yyyy", "MMMMM dd");

        String[] temp = input.split("/",2);
        assert temp.length == 2;
        StringBuilder fullCommand = new StringBuilder(temp[0]);
        StringBuilder rawDate = new StringBuilder(temp[1]);

        Date date = null;
        for (int i = 0; i < formatStrings.size(); i++) {
            try {
                DateFormat df = new SimpleDateFormat(formatStrings.get(i));
                df.setLenient(false);
                date = df.parse(rawDate.toString().substring(3));
                DateFormat formatter = new SimpleDateFormat(outputFormats.get(i));
                fullCommand.append("/").append(IDENTIFIER).append(formatter.format(date));
                break;

            } catch (ParseException e) {
                continue;
            }
        }
        if (date == null) {
            throw new DukeException("Time format not acceptable! Please use dd/mm/yy format");
        } else {
            return fullCommand.toString();
        }
    }




}
