package dukepkg;

import dukepkg.commands.*;
import dukepkg.exceptions.DukeException;
import dukepkg.exceptions.FormatException;
import dukepkg.exceptions.UnrecognizedException;

import java.util.ArrayList;
import java.util.Scanner;


public class Parser {
    Scanner input;
    public Parser() {

    }

    public String getAction(String[] arr) {
        return arr[0];
    }

    public String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    public void validateList(String[] arr) throws FormatException {
        if (arr.length > 1) {
            throw new FormatException("☹ OOPS!!! The list command should just be \"list\".");
        }
    }

    public void validateBye(String[] arr) throws FormatException {
        if (arr.length > 1) {
            throw new FormatException("☹ OOPS!!! The bye command should just be \"bye\".");
        }
    }

    public void unrecognizedAction() throws UnrecognizedException {
        throw new UnrecognizedException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void validateModifyExistingTaskCommandFormat(String[] arr) throws FormatException {
        if (arr.length == 1 || arr.length > 2) {
            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be " + arr[0] + " + task No.");
        }
        if (!arr[1].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be followed by a integer.");
        }
    }

    public static void validateModifyExistingTaskCommandIndex(int index, int listLength) throws FormatException {
        if(index >= listLength || index < 0) {
            throw new FormatException("☹ OOPS!!! The task No. you refer to is non-existent. Try another one.");
        }
    }

    public void validateAddTaskCommandLength(String[] arr) throws FormatException {
        if (arr.length == 1) {
            throw new FormatException("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
        }
    }

    public static void validateDeadlineFormat(String[] arr) throws FormatException {
        String ddl[] = arr[1].trim().split("/by", 2);
        if(ddl.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be deadline description /by time.");
        } else if(arr[1].trim().matches("/by.*")) {
            throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
        } else if(arr[1].trim().matches(".*/by")) {
            throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
        }
    }

    public static Task standardizeDeadlineTime(String[] arr) {
        String ddl[] = arr[1].trim().split("/by", 2);
        Task t;
        String date_time[] = ddl[1].trim().split(" ");
        if(date_time.length > 1 && date_time[0].matches("^((((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((20[0-9][0-9]))|(((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((19[0-9][0-9]))|(29/(02|2)/20(([02468][048])|([13579][26])))|(29/(02|2)/19(([02468][048])|([13579][26]))))$")  && date_time[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9])[0-5][0-9]$")) {
            String time = formatTime(ddl[1].trim());
            t = new Deadline(ddl[0].trim(), time);
        } else {
            t = new Deadline(ddl[0].trim(), ddl[1].trim());
        }
        return t;
    }

    public static void validateEventFormat(String[] arr) throws FormatException {
        String evt[] = arr[1].trim().split("/at", 2);
        if(evt.length < 2) {
            throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be event description /at time.");
        } else if(arr[1].trim().matches("/at.*")) {
            throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
        } else if(arr[1].trim().matches(".*/at")) {
            throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
        }
    }

    public static Task standardizeEventTime(String[] arr) {
        Task t;
        String evt[] = arr[1].trim().split("/at", 2);
        String date_time[] = evt[1].trim().split(" ");
        if(date_time.length > 1 && date_time[0].matches("^((((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((20[0-9][0-9]))|(((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((19[0-9][0-9]))|(29/(02|2)/20(([02468][048])|([13579][26])))|(29/(02|2)/19(([02468][048])|([13579][26]))))$")  && date_time[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9])[0-5][0-9]$")) {
            String time = formatTime(evt[1].trim());
            t = new Event(evt[0].trim(), time);
        } else {
            t = new Event(evt[0].trim(), evt[1].trim());
        }
        return t;
    }

    public static void checkDuplicate(ArrayList<Task> tasks, Task t) throws FormatException {
        for(Task existing_t : tasks) {
            if(existing_t.equals(t)) {
                throw new FormatException("☹ OOPS!!! No duplicate tasks.\n" +
                        "The existing task is:\n" + existing_t.toString());
            }
        }
    }

    private static String formatTime(String input) {
        String date_time[] = input.split(" ");
        String date[] = date_time[0].split("/");
        int day = Integer.parseInt(date[0]);
        int month_int = Integer.parseInt(date[1]);
        String month_enum = Month.convertIntToString(month_int);
        String month_str = month_enum.substring(0,1) + month_enum.substring(1).toLowerCase();
        int year = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(date_time[1].substring(0,2));
        int min = Integer.parseInt(date_time[1].substring(2,3));
        String output = String.valueOf(day);
        output += day % 10 == 1 ? "st of " : day % 10 == 2 ? "nd of " : day % 10 == 3 ? "rd of " : "th of ";
        output += month_str + " " + year + ", ";
        output += (hour > 12 ? hour - 12 : hour) + (min == 0 ? "" : "." + min) + (hour >= 12 ? "pm" : "am");
        return output;
    }

    public Command constructCommand(String command) throws DukeException {
        String arr[] = parseCommand(command);
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
                default:
                    unrecognizedAction();
                    return new ByeCommand(); // not reachable
            }
    }

    public enum Month {
        JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);
        public final int id;
        Month(int id) {
            this.id = id;
        }
        public static String convertIntToString(int iMonth) {
            for(Month month : Month.values()) {
                if(month.id == iMonth) {
                    return month.toString();
                }
            }
            return "";
        }
    }
}
