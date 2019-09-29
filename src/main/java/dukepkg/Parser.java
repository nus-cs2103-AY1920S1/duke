package dukepkg;

import dukepkg.commands.*;
import dukepkg.exceptions.DukeException;
import dukepkg.exceptions.FormatException;
import dukepkg.exceptions.UnrecognizedException;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Parser used to make sense of the command.
 */
@SuppressWarnings("ALL")
public class Parser {
    /**
     * The Scanner used to take in inputs.
     */
    Scanner input;

    /**
     * Instantiates a new Parser.
     */
    public Parser() {

    }

    private String getAction(String[] arr) {
        return arr[0];
    }

    private String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    private void validateList(String[] arr) throws FormatException {
        if (arr.length > 1) {
            throw new FormatException("☹ OOPS!!! The list command should just be \"list\".");
        }
    }

    private void validateBye(String[] arr) throws FormatException {
        if (arr.length > 1) {
            throw new FormatException("☹ OOPS!!! The bye command should just be \"bye\".");
        }
    }

    private void unrecognizedAction() throws UnrecognizedException {
        throw new UnrecognizedException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void validateModifyExistingTaskCommandFormat(String[] arr) throws FormatException {
        if (arr.length == 1 || arr.length > 2) {
            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be " + arr[0] + " + task No.");
        }
        if (!arr[1].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be followed by a integer.");
        }
    }

    /**
     * Check whether the index used to modify existing tasks is out of range of the tasklist length.
     *
     * @param index      the index of the task in the list
     * @param listLength the list length
     * @throws FormatException if the index is out of range.
     */
    public static void validateModifyExistingTaskCommandIndex(int index, int listLength) throws FormatException {
        if(index >= listLength || index < 0) {
            throw new FormatException("☹ OOPS!!! The task No. you refer to is non-existent. Try another one.");
        }
    }

    private void validateAddTaskCommandLength(String[] arr) throws FormatException {
        if (arr.length == 1) {
            throw new FormatException("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
        }
    }

    /**
     * Validates deadline format.
     *
     * @param arr the user input line.
     * @throws FormatException thrown if deadline task format is wrong.
     */
    public static void validateDeadlineFormat(String[] arr) throws FormatException {
        String[] ddl = arr[1].trim().split("/by", 2);
        if(ddl.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be deadline description /by time.");
        } else if(arr[1].trim().matches("/by.*")) {
            throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
        } else if(arr[1].trim().matches(".*/by")) {
            throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
        }
    }

    /**
     * Validates time format for the TimeCommand that contains duration of tasks.
     *
     * @param arr the arr that contains type of command and task content
     * @throws FormatException the format exception
     */
    public static void validateTimeFormat(String[] arr) throws FormatException {
        String[] ddl = arr[1].trim().split("/for", 2);
        if(ddl.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be todo description /for duration.");
        } else if(arr[1].trim().matches("/for.*")) {
            throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
        } else if(arr[1].trim().matches(".*/for")) {
            throw new FormatException("☹ oops!!! you forget to add duration for the " + arr[0] + " command.");
        }
    }

    /**
     * Standardize time for the deadline task.
     *
     * @param arr the user input line.
     * @return the Deadline task created after time format is standardized.
     */
    @SuppressWarnings("Annotator")
    public static Task standardizeDeadlineTime(String[] arr) {
        String[] ddl = arr[1].trim().split("/by", 2);
        Task t;
        String[] date_time = ddl[1].trim().split(" ");
        //noinspection Annotator
        if(date_time.length > 1 && date_time[0].matches("^((((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((20[0-9][0-9]))|(((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((19[0-9][0-9]))|(29/(02|2)/20(([02468][048])|([13579][26])))|(29/(02|2)/19(([02468][048])|([13579][26]))))$")  && date_time[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9])[0-5][0-9]$")) {
            String time = formatTime(ddl[1].trim());
            t = new Deadline(ddl[0].trim(), time);
        } else {
            t = new Deadline(ddl[0].trim(), ddl[1].trim());
        }
        return t;
    }

    /**
     * Validate event format.
     *
     * @param arr the user input line.
     * @throws FormatException if user input has wrong format for event tasks.
     */
    public static void validateEventFormat(String[] arr) throws FormatException {
        String[] evt = arr[1].trim().split("/at", 2);
        if(evt.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be event description /at time.");
        } else if(arr[1].trim().matches("/at.*")) {
            throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
        } else if(arr[1].trim().matches(".*/at")) {
            throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
        }
    }

    /**
     * Standardize event time task.
     *
     * @param arr the user input line
     * @return the event task created after time format is standardized
     */
    public static Task standardizeEventTime(String[] arr) {
        Task t;
        String[] evt = arr[1].trim().split("/at", 2);
        String[] date_time = evt[1].trim().split(" ");
        if(date_time.length > 1 && date_time[0].matches("^((((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((20[0-9][0-9]))|(((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((19[0-9][0-9]))|(29/(02|2)/20(([02468][048])|([13579][26])))|(29/(02|2)/19(([02468][048])|([13579][26]))))$")  && date_time[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9])[0-5][0-9]$")) {
            String time = formatTime(evt[1].trim());
            t = new Event(evt[0].trim(), time);
        } else {
            t = new Event(evt[0].trim(), evt[1].trim());
        }
        return t;
    }

    /**
     * Check if the new task is a duplicate of one already saved in tasklist.
     *
     * @param tasks the tasks alreday saved in the tasklist.
     * @param t     the new task that is to be added.
     * @throws FormatException when the new task is a duplicate.
     */
    public static void checkDuplicate(ArrayList<Task> tasks, Task t) throws FormatException {
        for(Task existing_t : tasks) {
            if(existing_t.equals(t)) {
                throw new FormatException("☹ OOPS!!! No duplicate tasks.\n" +
                        "The existing task is:\n" + existing_t.toString());
            }
        }
    }

    /**
     * Format time string from DD/MM/YYYY TTTT to word form, such as 9th of Sempterber, 2019, 6pm.
     *
     * @param input the previous time format
     * @return the standardized time format
     */
    static String formatTime(String input) {
        String[] date_time = input.split(" ");
        String[] date = date_time[0].split("/");
        int day = Integer.parseInt(date[0]);
        int month_int = Integer.parseInt(date[1]);
        String month_enum = Month.convertIntToString(month_int);
        String month_str = month_enum.substring(0,1) + month_enum.substring(1).toLowerCase();
        int year = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(date_time[1].substring(0,2));
        int min = Integer.parseInt(date_time[1].substring(2,4));
        String output = String.valueOf(day);
        output += day % 10 == 1 ? "st of " : day % 10 == 2 ? "nd of " : day % 10 == 3 ? "rd of " : "th of ";
        output += month_str + " " + year + ", ";
        output += (hour > 12 ? hour - 12 : hour) + (min == 0 ? "" : "." + min) + (hour >= 12 ? "pm" : "am");
        return output;
    }

    /**
     * Construct user command object based on input line.
     *
     * @param command the user input line.
     * @return the command constructed
     * @throws DukeException if the user input has wrong format or is not even recognizeable.
     */
    public Command constructCommand(String command) throws DukeException {
        String[] arr = parseCommand(command);
        String action = getAction(arr);
            switch(action) {
                case "list":
                    validateList(arr);
                    return new ListCommand();
                case "bye":
                    validateBye(arr);
                    return new ByeCommand();
                case "done":
                case "delete":
                    validateModifyExistingTaskCommandFormat(arr);
                    return Command.getModifyExistingTaskCommand(arr);
                case "todo":
                case "deadline":
                case "event":
                    validateAddTaskCommandLength(arr);
                    return Command.getAddTaskCommand(arr);
                case "find":
                    validateFindTaskCommandFormat(arr);
                    return Command.getFindTaskCommand(arr);
                default:
                    unrecognizedAction();
                    return new ByeCommand(); // not reachable
            }
    }

    private void validateFindTaskCommandFormat(String[] arr) throws FormatException {
        if(arr.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be done keywards.");
        }
    }

    /**
     * The enum Month.
     */
    public enum Month {
        /**
         * January month.
         */
        JANUARY(1),
        /**
         * February month.
         */
        FEBRUARY(2),
        /**
         * March month.
         */
        MARCH(3),
        /**
         * April month.
         */
        APRIL(4),
        /**
         * May month.
         */
        MAY(5),
        /**
         * June month.
         */
        JUNE(6),
        /**
         * July month.
         */
        JULY(7),
        /**
         * August month.
         */
        AUGUST(8),
        /**
         * September month.
         */
        SEPTEMBER(9),
        /**
         * October month.
         */
        OCTOBER(10),
        /**
         * November month.
         */
        NOVEMBER(11),
        /**
         * December month.
         */
        DECEMBER(12);
        /**
         * The months expressed in integers.
         */
        final int id;
        Month(int id) {
            this.id = id;
        }

        /**
         * Convert the expression format of month from int to string.
         *
         * @param iMonth the month expressed in integers.
         * @return the month expressed in string.
         */
        static String convertIntToString(int iMonth) {
            for(Month month : Month.values()) {
                if(month.id == iMonth) {
                    return month.toString();
                }
            }
            return "";
        }
    }
}
