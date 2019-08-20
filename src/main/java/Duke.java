import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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

            if (command.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tHere are the tasks in your list: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\n\t" + (i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (command.equals("done")) {
                // Get number
                int taskNum = Integer.valueOf(sc.next());
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

                // Create DeadLine object
                DeadLine d = new DeadLine(wordArr[0], wordArr[1]);
                taskList.add(d);
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tGot it! I've added this task: ");
                System.out.println("\n\t" + d.toString());
                System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                System.out.println("\t____________________________________________________________\n");

            } else if (command.equals("event")) {

                String str = sc.nextLine();
                String[] wordArr = str.split("/at", 2);

                // Create Event Object
                Event e = new Event(wordArr[0], wordArr[1]);
                taskList.add(e);
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tGot it! I've added this task: ");
                System.out.println("\n\t" + e.toString());
                System.out.println("\n\tNow you have " + taskList.size() + " tasks in the list.");
                System.out.println("\t____________________________________________________________\n");
            }
        }

        sc.close();

    }
}