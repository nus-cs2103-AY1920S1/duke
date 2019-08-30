import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    protected static Scanner sc;
    protected static ArrayList<Task> taskList;
    protected static boolean byeFlag = false;

    public static void main(String[] args) {
        taskList = new ArrayList<Task>();
        sc = new Scanner(System.in);
        while (!byeFlag) {
            try {
                duke();
            } catch (DukeException ex) {
                System.err.println(ex);
            }
        }
    }

    public static void duke() throws DukeException {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String command = getCommand(input);
            String description;
            DateTime dateTime;

            switch (command) {
                case "list":
                    list();
                    break;
                case "done":
                    description = getDescription(input, command);
                    done(description);
                    break;
                case "todo":
                    description = getDescription(input, command);
                    updateAdd(todo(description));
                    break;
                case "deadline":
                    description = getDescription(input, command);
                    dateTime = getDateAndTime(input, command);
                    updateAdd(deadline(description, dateTime));
                    break;
                case "event":
                    description = getDescription(input, command);
                    dateTime = getDateAndTime(input, command);
                    updateAdd(event(description, dateTime));
                    break;
                case "delete":
                    description = getDescription(input, command);
                    updateRemove(delete(description));
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        byeFlag = true;
    }

    public static String getCommand(String input) {
        String command;

        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            command = input.substring(0, index);
        } else {
            command = input;
        }

        return command;
    }

    public static String getDescription(String input, String command) throws DukeException {
        String action = "";

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ") + 1;
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            action = split[0];
        } else if (input.contains(" ")) {
            String[] split = input.split(" ", 2);
            action = split[1];
        }

        if (action.equals("")) {
            throw new DukeException("OOPS!!! The description of a " + command + " cannot be empty.");
        }

        return action;
    }

    public static DateTime getDateAndTime(String input, String command) throws DukeException {
        String detail = "";
        DateTime dateTime;

        if (input.contains(" ") && input.contains("/")) {
            int index = input.indexOf(" ");
            String removeCommand = input.substring(index);
            String[] split = removeCommand.split("/", 2);
            index = split[1].indexOf(" ") + 1;
            detail = split[1].substring(index);
        }

        if (detail.equals("")) {
            throw new DukeException("OOPS!!! The time and date of a " + command + " cannot be empty.");
        } else {
            dateTime = new DateTime(detail);
            dateTime.setDateAndTime();
        }

        return dateTime;
    }

    public static void updateAdd(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        if (taskList.size() > 1) {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " task in the list.");
        }
    }

    public static void updateRemove(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        if (taskList.size() > 1) {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " task in the list.");
        }
    }


    public static void list() {
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                System.out.println(index + "." + taskList.get(i));
            }
        } else {
            System.out.println("You have no tasks in the list.");
        }
    }

    public static void done(String position) {
        int index = Integer.parseInt(position);
        Task task = taskList.get(index - 1);
        task.setDone(true);
    }

    public static Task todo(String action) {
        Task task = new Todo(action);
        taskList.add(task);

        return task;
    }

    public static Task deadline(String action, DateTime dateTime) {
        Task task = new Deadline(action, dateTime);
        taskList.add(task);

        return task;
    }

    public static Task event(String action, DateTime dateTime) {
        Task task = new Event(action, dateTime);
        taskList.add(task);

        return task;
    }

    public static Task delete(String position) throws DukeException {
        int index = Integer.parseInt(position) - 1;
        Task task;

        if (index < taskList.size()) {
            task = taskList.get(index);
            taskList.remove(index);
        } else {
            throw new DukeException("OOPS!!! The task you want to remove does not exist!");
        }

        return task;
    }
}