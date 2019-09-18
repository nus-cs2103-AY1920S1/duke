package duke.logic;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.ByeCommand;
import duke.command.PriorityCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser parses the commands in Ui.
 */
public class Parser {


    /**
     * Returns the correct command from given String command.
     * @param command is the String that needs to be parsed.
     * @return approriate command.
     * @throws DukeException when command is invalid.
     */
    public static Command parse(String command) throws DukeException {

        if (command.equals("bye")) {
            return new ByeCommand();
        }

        isCommandValid(command);

        String firstWord = command.split(" ")[0];

        if (firstWord.equals("done")) {
            String secondWord = command.split(" ")[1];
            int num = Integer.parseInt(secondWord);

            return new DoneCommand(num);

        } else if (firstWord.equals("todo")) {
            int spaceIndex = command.indexOf(" ");
            ToDo todo = getToDo(command.substring(spaceIndex + 1));

            return new AddCommand(todo);

        } else if (firstWord.equals("deadline")) {

            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/");
            String datetime = command.substring(slashIndex + 4);

            if (! isDateTimeValid(datetime)) {
                throw new DukeException("Invalid datetime format");
            }

            Deadline deadline = getDeadline(command.substring(spaceIndex + 1, slashIndex - 1),
                    datetime);

            return new AddCommand(deadline);

        } else if (firstWord.equals("event")) {
            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/");
            String datetime = command.substring(slashIndex + 4);

            if (! isDateTimeValid(datetime)) {
                throw new DukeException("Invalid datetime format");
            }
            Event e = getEvent(command.substring(spaceIndex + 1, slashIndex - 1), datetime);

            return new AddCommand(e);

        } else if (firstWord.equals("delete")) {
            String num = command.split(" ")[1];
            return new DeleteCommand(Integer.parseInt(num));

        } else if (firstWord.equals("find")) {
            int spaceIndex = command.indexOf(" ");

            return new FindCommand(command.substring(spaceIndex + 1));
        } else if (firstWord.equals("priority")) {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            int priorityNumber = Integer.parseInt(command.split(" ")[2]);

            return new PriorityCommand(taskNum, priorityNumber);
        }

        return new ListCommand();
    }

    private static boolean isDateTimeValid(String datetime) {
        if (! datetime.contains(" ")) {
            return false;
        }
        String[] stringArr = datetime.split(" ");
        if ((stringArr.length != 4)
            || (Integer.parseInt(stringArr[0]) > 31)
            || (! isValidMonth(stringArr[1]))
            || (! isNumeric(stringArr[2]))
            || (stringArr[2].length() != 4)
            || (stringArr[3].length() != 4)) {
            return false;
        }

        return true;
    }

    private static boolean isValidMonth(String month) {
        String[] months = {
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun",
            "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"
        };
        for (String str: months) {
            if (str.equals(month)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns a new ToDo object.
     * @param taskName for initializing ToDo instance.
     * @return new ToDo instance.
     */
    public static ToDo getToDo(String taskName) {
        return new ToDo(taskName);

    }

    /**
     * Returns a new Deadline object.
     * @param taskName for initializing Deadline instance.
     * @param datetime to be parsed into suitable LocalDateTime object.
     * @return new Deadline instance.
     */
    public static Deadline getDeadline(String taskName, String datetime) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);
        return new Deadline(taskName, dateTimeObj);

    }

    /**
     * Returns a new Event object.
     * @param taskName for initializing Event instance.
     * @param datetime to be parsed into suitable LocalDateTime object.
     * @return new Event instance.
     */
    public static Event getEvent(String taskName, String datetime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);

        return new Event(taskName, dateTimeObj);
    }

    private static boolean isCommandValid(String str) throws DukeException {
        String firstWord = str.split(" ")[0];
        if (! (firstWord.equals("list")
            || firstWord.equals("todo")
            || firstWord.equals("deadline")
            || firstWord.equals("event")
            || firstWord.equals("delete")
            || firstWord.equals("find")
            || firstWord.equals("done")
            || firstWord.equals("priority"))) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

        } else if (! firstWord.equals("list") && str.split(" ").length == 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.",
                    str.split(" ")[0]));

        } else if (firstWord.equals("deadline") && ! str.contains("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline has to be followed by '/by'.");

        } else if (firstWord.equals("event") && ! str.contains("/at")) {
            throw new DukeException("OOPS!!! The description of an event has to be followed by '/at'.");

        } else if (firstWord.equals("deadline") && str.split(" ")[1].equals("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");

        } else if (firstWord.equals("event") && str.split(" ")[1].equals("/at")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");

        } else if (firstWord.equals("delete") && ! isNumeric(str.split(" ")[1])) {
            throw new DukeException("OOPS!!! The index of the array has to be specified.");

        } else if (firstWord.equals("delete") && isNumeric(str.split(" ")[1])
                && (Integer.parseInt(str.split(" ")[1]) <= 0)) {
            throw new DukeException("OOPS!!! Index out of bounds. It is larger or smaller than size of list.");

        } else if (firstWord.equals("delete") && str.split(" ").length > 2) {
            throw new DukeException("OOPS!!! Please key in 'delete x', where x is the index that you want to delete!");
        }

        return true;
    }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
