import DukePkg.Task;
import DukePkg.Deadline;
import DukePkg.Event;
import DukePkg.Todo;
import DukePkg.FormatException;
import DukePkg.UnrecognizedException;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
public class Duke {
    public enum Month {
        JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);
        public final int id;
        Month(int id) {
            this.id = id;
        }
        public static String convertIntToString(int iMonth) {
            for(Month month : Month.values()) {
                if(month.id == iMonth) {
                    System.out.println("month's id: " + month.id + " current month: " + iMonth);
                    return month.toString();
                }
            }
            return "";
        }
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String greeting = "Hello! I'm Duke\n" +
                          "What can I do for you?";
        System.out.println(greeting);

        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String command = input.nextLine();
            String arr[] = command.split(" ", 2);
            try{
                switch(arr[0]) {
                    case "list":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The list command should just be \"list\".");
                        }
                        System.out.println("Here are the tasks in your list:");
                        int counter = 0;
                        for (Task t : tasks) {
                            counter++;
                            System.out.println(counter + ". " + t.toString());
                        }
                        break;
                    case "bye":
                        if (arr.length > 1) {
                            throw new FormatException("☹ OOPS!!! The bye command should just be \"bye\".");
                        }
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                    case "done":
                    case "delete":
                        if (arr.length == 1 || arr.length > 2) {
                            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be " + arr[0] + " + task No.");
                        }
                        if (!arr[1].matches("(?<=\\s|^)\\d+(?=\\s|$)")){
                            throw new FormatException("☹ OOPS!!! The " + arr[0] + " command should be followed by a integer.");
                        }
                        int index = Integer.parseInt(arr[1]) - 1;
                        if(index >= tasks.size() || index < 0) {
                            throw new FormatException("☹ OOPS!!! The task No. you refer to is non-existent. Try another one.");
                        }
                        String prompt;
                        if(arr[0].equals("done")){
                            tasks.get(index).markDone();
                            prompt = "Nice! I've marked this task as done:\n" +
                                    "    " + tasks.get(index).toString();
                        } else {
                            Task t = tasks.get(index);
                            tasks.remove(index);
                            prompt = "Noted. I've removed this task:\n" +
                                            "    " + t.toString() + "\n" +
                                            "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.";
                        }
                        System.out.println(prompt);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (arr.length == 1) {
                            throw new FormatException("☹ OOPS!!! The description of a " + arr[0] + " cannot be empty.");
                        }
                        Task t = new Todo(arr[1]);
                        if (!arr[0].equals("todo")) {
                            if(arr[0].equals("deadline")) {
                                String ddl[] = arr[1].trim().split("/by", 2);
                                if(ddl.length < 2) {
                                    throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be deadline description /by time.");
                                } else if(arr[1].trim().matches("/by.*")) {
                                    throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
                                } else if(arr[1].trim().matches(".*/by")) {
                                    throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
                                }
                                String date_time[] = ddl[1].trim().split(" ");
                                if(date_time.length > 1 && date_time[0].matches("^((((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((20[0-9][0-9]))|(((([1-9])|(0[1-9])|(1\\d)|(2[0-8]))/(([1-9])|(0[1-9])|(1[0-2])))|((31/(((0[13578])|([13578]))|(1[02])))|((29|30)/(((0[1,3-9])|([1,3-9]))|(1[0-2])))))/((19[0-9][0-9]))|(29/(02|2)/20(([02468][048])|([13579][26])))|(29/(02|2)/19(([02468][048])|([13579][26]))))$")  && date_time[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9])[0-5][0-9]$")) {
                                    String time = formatTime(ddl[1].trim());
                                    t = new Deadline(ddl[0].trim(), time);
                                } else {
                                    t = new Deadline(ddl[0].trim(), ddl[1].trim());
                                }
                            } else {
                                String evt[] = arr[1].trim().split("/at", 2);
                                if(evt.length < 2) {
                                    throw new FormatException("☹ OOPS!!! Format of " + arr[0] + " should be event description /at time.");
                                } else if(arr[1].trim().matches("/at.*")) {
                                    throw new FormatException("☹ oops!!! you forget to add description for the " + arr[0] + " command.");
                                } else if(arr[1].trim().matches(".*/at")) {
                                    throw new FormatException("☹ oops!!! you forget to add time for the " + arr[0] + " command.");
                                }
                                t = new Event(evt[0].trim(), evt[1].trim());
                            }
                        }

                        for(Task existing_t : tasks) {
                            if(existing_t.equals(t)) {
                                throw new FormatException("☹ OOPS!!! No duplicate tasks.\n" +
                                        "The existing task is:\n" + existing_t.toString());
                            }
                        }
                        tasks.add(t);
                        String output = "Got it. I've added this task:\n" +
                                "    " + t.toString() + "\n" +
                                "Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.";
                        System.out.println(output);
                        break;
                    default:
                        throw new UnrecognizedException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(FormatException e) {
                System.out.println(e);
            } catch(UnrecognizedException e) {
                System.out.println(e);
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
}
