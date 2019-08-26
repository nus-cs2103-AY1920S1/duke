package duke.logic;

import duke.command.*;
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
     * @param command
     * @return approriate command.
     * @throws DukeException
     */
    public static Command parse(String command) throws DukeException{


        if (command.equals("bye")) {
            return new ByeCommand();
        }
        isCommandValid(command);


        if (command.split(" ")[0].equals("done")) {

            int num = Integer.parseInt(command.split(" ")[1]);

            return new DoneCommand(num);

        } else if (command.split(" ")[0].equals("todo")) {
            int spaceIndex = command.indexOf(" ");
            ToDo todo = getToDo(command.substring(spaceIndex + 1));

            return new AddCommand(todo);

        } else if (command.split(" ")[0].equals("deadline")) {

            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/");
            Deadline deadline = getDeadline(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));

            return new AddCommand(deadline);

        } else if (command.split(" ")[0].equals("event")) {
            int spaceIndex = command.indexOf(" ");
            int slashIndex = command.indexOf("/");
            Event e = getEvent(command.substring(spaceIndex + 1, slashIndex - 1), command.substring(slashIndex + 4));

            return new AddCommand(e);

        } else if (command.split(" ")[0].equals("delete")) {

            return new DeleteCommand(Integer.parseInt(command.split(" ")[1]));
        }

        return new ListCommand();



    }

    public static ToDo getToDo(String taskName) {
        return new ToDo(taskName);

    }

    public static Deadline getDeadline(String taskName, String datetime) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);
        return new Deadline(taskName, dateTimeObj);

    }

    public static Event getEvent(String taskName, String datetime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        LocalDateTime dateTimeObj =  LocalDateTime.parse(datetime, dtf);

        return new Event(taskName, dateTimeObj);
    }

    private static boolean isCommandValid(String str) throws DukeException {


        if (! (str.split(" ")[0].equals("list") ||
                str.split(" ")[0].equals("todo") ||
                str.split(" ")[0].equals("deadline") ||
                str.split(" ")[0].equals("event") ||
                str.split(" ")[0].equals("delete") ||
                str.split(" ")[0].equals("done")
        )
        ) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! str.split(" ")[0].equals("list") && str.split(" ").length == 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", str.split(" ")[0]));

        } else if (str.split(" ")[0].equals("deadline") && ! str.contains("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline has to be followed by '/by'.");

        } else if (str.split(" ")[0].equals("event") && ! str.contains("/at")) {
            throw new DukeException("OOPS!!! The description of an event has to be followed by '/at'.");

        } else if (str.split(" ")[0].equals("deadline") && str.split(" ")[1].equals("/by")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");

        } else if (str.split(" ")[0].equals("event") && str.split(" ")[1].equals("/at")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");

        } else if (str.split(" ")[0].equals("delete") &&  ! isNumeric(str.split(" ")[1])) {
            throw new DukeException("OOPS!!! The index of the array has to be specified.");
        } else if (str.split(" ")[0].equals("delete") && isNumeric(str.split(" ")[1])
                && (Integer.parseInt(str.split(" ")[1]) <= 0 )) {
            throw new DukeException("OOPS!!! Index out of bounds. It is larger or smaller than size of list.");
        } else if (str.split(" ")[0].equals("delete") && str.split(" ").length > 2) {
            throw new DukeException("OOPS!!! Please key in 'delete x', where x is the index that you want to delete!");
        }

        return true;
    }

    private static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
}
