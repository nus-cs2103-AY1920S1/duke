import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Duke {

    private static String getSuffix(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int remainder = day % 10;
        if (remainder == 1) {
            return "st";
        } else if (remainder == 2) {
            return "nd";
        } else if (remainder == 3) {
            return "rd";
        } else {
            return "th";
        }
    }

    private static String getDate(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        String suffix = getSuffix(dateTime) + " of";

        DateTimeFormatter wantedFormat = DateTimeFormatter.ofPattern(" d'" + suffix + "' MMMM yyyy, h.mm a");
        String formattedDate = wantedFormat.format(dateTime);
        return formattedDate;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        // Echo commands until bye is entered

        while (true) {

            String command = sc.next();

            try {
                if (command.equals("list")) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tHere are the tasks in your list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\n\t" + (i + 1) + ". " + taskList.get(i).toString());
                    }
                    System.out.println("\t____________________________________________________________\n");

                } else if (command.equals("done")) {
                    int taskNum = Integer.valueOf(sc.next());
                    if (taskNum < 0 || taskNum > taskList.size()) {
                        throw new DukeException("OOPS! Integer is out of range of list.");
                    }
                    taskList.get(taskNum).setAsDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tNice! I have marked this task as done: ");
                    System.out.println("\n\t" + taskList.get(taskNum));
                    System.out.println("\t____________________________________________________________\n");

                } else if (command.equals("bye")) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tBye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________\n");
                    break;

                } else if (command.equals("todo")) {

                    String str = sc.nextLine();

                    if (str.equals("")) {
                        throw new DukeException("OOPS! The description for todo should not be empty.");
                    }

                    // Create ToDo object
                    ToDo toDoTask = new ToDo(str);
                    taskList.add(toDoTask);

                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + toDoTask.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                } else if (command.equals("deadline")) {

                    String str = sc.nextLine();
                    String[] wordArr = str.split("/by", 2);

                    if (wordArr.length == 1) {
                        throw new DukeException("OOPS! Deadlines should be followed by a /by.");
                    }

                    // Format date accordingly
                    String date = getDate(wordArr[1].stripLeading());

                    // Create DeadLine object
                    DeadLine d = new DeadLine(wordArr[0], date);
                    taskList.add(d);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + d.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                } else if (command.equals("event")) {

                    String str = sc.nextLine();
                    String[] wordArr = str.split("/at", 2);

                    if (wordArr.length == 1) {
                        throw new DukeException("OOPS! Events should be followed by a /at.");
                    }

                    // Format date accordingly
                    String date = getDate(wordArr[1].stripLeading());

                    // Create Event Object
                    Event e = new Event(wordArr[0], date);
                    taskList.add(e);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tGot it! I've added this task: ");
                    System.out.println("\n\t" + e.toString());
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                } else if (command.equals("delete")) {

                    int taskNum = Integer.valueOf(sc.next());
                    if (taskNum < 0 || taskNum > taskList.size()) {
                        throw new DukeException("OOPS! Integer is out of range of list.");
                    }
                    Task removed = taskList.get(taskNum);
                    taskList.remove(taskNum);
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\n\tNoted. I have removed this task: ");
                    System.out.println("\n\t" + removed);
                    System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                    System.out.println("\t____________________________________________________________\n");

                }

                else {
                    throw new DukeException("OOPS! I'm sorry, I don't know what that means! :(");
                }
            } catch (NumberFormatException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tOOPS! An integer is expected after done / delete.");
                System.out.println("\t____________________________________________________________\n");
            } catch (DukeException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + e.getMessage());
                System.out.println("\t____________________________________________________________\n");
            }
        }

        sc.close();

    }
}