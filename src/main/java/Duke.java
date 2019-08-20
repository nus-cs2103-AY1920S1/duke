import java.util.ArrayList;
import java.util.Scanner;

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

            String[] dueSplit = input.split("/");
            String due = "dummy";
            if (dueSplit.length > 1) {
                due = dueSplit[1];
            }
            String[] doneMarkers = dueSplit[0].split(" ", 2);
            String userCommand = doneMarkers[0].toLowerCase();
            String taskDescription = "dummy";
            if (doneMarkers.length > 1) {
                taskDescription = doneMarkers[1];
            }

            if(userCommand.equals("done")) {
                Task taskDone = taskStorage.get(Integer.valueOf(taskDescription) - 1);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done: " + "\n"
                        + "    " + taskDone + "\n");
            } else if (check.equals("bye") == false) {
                Task t = new Task("");
                if (userCommand.equals("todo")) {
                    t = new Todo(taskDescription);
                    taskStorage.add(t);
                } else if (userCommand.equals("deadline")) {
                    t = new Deadline(taskDescription, due);
                    taskStorage.add(t);
                } else if (userCommand.equals("event")) {
                    t = new Event(taskDescription, due);
                    taskStorage.add(t);
                }

                taskCount = Task.getTaskCount();
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + t);
                System.out.println(taskCounter(taskCount) + "\n");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String taskCounter(int taskCount) {
        if (taskCount > 1) {
            return ("Now you have " + taskCount + " tasks in the list.");
        } else {
            return ("Now you have " + taskCount + " task in the list.");
        }
    }
}
