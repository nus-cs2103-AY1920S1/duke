import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;

/**
 * Entry point of this project Duke. Duke is a Task manager that aims
 * to serve as an efficient way to manage our day to day tasks. It supports multiple types of task
 * such as todo reminders, tasks with a deadline and even an event.
 * The Duke task manager has many iterations, it is
 * continuously evolving and becoming smarter to cater to it users' needs.
 *
 * @author TuanDingWei
 */
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        String check = "dummy";
        ArrayList<Task> taskStorage = new ArrayList<>();
        int taskCount;

        while (check.equals("bye") == false) {
             try {
                 input = sc.nextLine();
                 check = input.toLowerCase();
                 if (check.equals("list")) {
                     System.out.println("Here are the tasks in your list:");
                     for (int i = 1; i <= taskStorage.size(); i++) {
                         Task evaluatingTask = taskStorage.get(i - 1);
                         System.out.println(i + "." + evaluatingTask.toString());
                     }
                     System.out.println();
                     continue;
                 }

                 String[] dueSplit = input.split("/", 2);
                 String due = "dummy";
                 if (dueSplit.length > 1) {
                     due = dueSplit[1].split(" ", 2)[1];
                 }
                 String[] doneMarkers = dueSplit[0].split(" ", 2);
                 String userCommand = doneMarkers[0].toLowerCase();
                 String taskDescription = "dummy";
                 if (doneMarkers.length > 1) {
                     taskDescription = doneMarkers[1];
                 }

                 if (userCommand.equals("done")) {
                     int target = Integer.valueOf(taskDescription);
                     Task taskDone;
                     if (taskStorage.size() >= target && target > 0) {
                         taskDone = taskStorage.get(target - 1);
                     } else {
                         throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                     }
                     taskDone.markAsDone();
                     System.out.println("Nice! I've marked this task as done: " + "\n"
                             + "    " + taskDone + "\n");
                 } else if (userCommand.equals("delete")) {
                     int target = Integer.valueOf(taskDescription);
                     Task taskDelete;
                     if (taskStorage.size() >= target && target > 0) {
                         taskDelete = taskStorage.get((target - 1));
                         removeTask(taskStorage, (target - 1));
                     } else {
                         throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                     }
                     Task.reduceTaskCount();

                     System.out.println("Noted. I've removed this task:" + "\n"
                             + "    " + taskDelete);
                     taskCount = Task.getTaskCount();
                     System.out.println(taskCounter(taskCount) + "\n");
                 } else if (check.equals("bye") == false) {
                     Task t = new Task("");
                     if (userCommand.equals("todo")) {
                         if (taskDescription.equals("dummy")) {
                             throw new EmptyToDoDescriptionException("The description of a todo cannot be empty.");
                         }
                         t = new Todo(taskDescription);
                         taskStorage.add(t);
                     } else if (userCommand.equals("deadline")) {
                         Date dateDue = convertStringToDate(due);
//                         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                         System.out.println(format.format(dateDue));
                         if (taskDescription.equals("dummy")) {
                             throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                         } else if (due.equals("dummy")) {
                             throw new EmptyDueDateException("The due date and time of this deadline is not specified.");
                         }
                         t = new Deadline(taskDescription, dateDue);
                         taskStorage.add(t);
                     } else if (userCommand.equals("event")) {
                         String[] eventStartEnd = due.split("-", 2);
                         Date start = convertStringToDate(eventStartEnd[0]);
                         Date end = convertStringToDate(eventStartEnd[1]);

                         if (taskDescription.equals("dummy")) {
                             throw new EmptyDescriptionException("The description of a event cannot be empty.");
                         } else if (due.equals("dummy")) {
                             throw new EmptyDueDateException("The due date and time of this task are not specified.");
                         }
                         t = new Event(taskDescription, start, end);
                         taskStorage.add(t);
                     } else {
                         throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                     }

                     taskCount = Task.getTaskCount();
                     System.out.println("Got it. I've added this task:");
                     System.out.println("    " + t);
                     System.out.println(taskCounter(taskCount) + "\n");
                 }
             } catch (DukeException ex) {
                System.out.println("OOPS!!! " + ex.getMessage() + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the task count.
     * @param taskCount The total number of Task that exist in the list.
     * @return An message that tells the user the total number of tasks are in the list.
     */
    private static String taskCounter(int taskCount) {
        if (taskCount > 1) {
            return ("Now you have " + taskCount + " tasks in the list.");
        } else {
            return ("Now you have " + taskCount + " task in the list.");
        }
    }

    /**
     * Removes task from the list.
     * @param taskStorage A list that stores all the existing tasks.
     *                    It is in the format of an ArrayList.
     * @param index The index of task that the user wishes to delete.
     */
    private static void removeTask(ArrayList taskStorage, int index) {
        taskStorage.remove(index);
    }

    private static Date convertStringToDate(String input) {
        Date date = new Date();
        try {
            if (input.length() <= 5) {
                SimpleDateFormat formatTimeOnly = new SimpleDateFormat("HHmm");
                date = formatTimeOnly.parse(input.trim());
                return date;
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm", Locale.ENGLISH);
                date = format.parse(input);
                return date;
            }
        } catch (ParseException e) {
            e.printStackTrace();
//            System.out.println("The format of your due date seems to be incorrect.");
        }
        return date;
    }
}
