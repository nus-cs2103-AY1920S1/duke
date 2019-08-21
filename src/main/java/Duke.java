import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String lines = "    ____________________________________________________________\n";
        String indent = "    ";
        String logo = lines
                    + "     ____        _           \n"
                    + "    |  _ \\ _   _| | _____   \n"
                    + "    | | | | | | | |/ / _ \\  \n"
                    + "    | |_| | |_| |   <  __/   \n"
                    + "    |____/ \\__,_|_|\\_\\___|\n"
                    + "     Hello! I'm Duke          \n"
                    + "     What can I do for you?   \n"
                    + lines;
        System.out.println(logo);
        String command = sc.nextLine();
        String[] comd = command.split("\\s");
        while (!comd[0].equals("bye")) {
            if (comd[0].isEmpty()) {}
            else if (comd[0].equals("list")) {
                System.out.print(lines);
                System.out.println(indent + " Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(indent + " " + (i + 1) + "." + taskList.get(i));
                }
                System.out.println(lines);
            } else if (comd[0].equals("done")) {
                int n = Integer.parseInt(comd[1]) - 1;
                taskList.get(n).markAsDone();
                System.out.print(lines);
                System.out.println(indent   + "Nice! I've marked this task as done: \n" +
                        indent   + "  " + taskList.get(n));
                System.out.println(lines);
            } else {
                Task task = new Task();
                if (comd[0].equals("todo")) {
                    String event = command.substring(5);
                    task = new ToDo(event);
                    taskList.add(task);
                } else if (comd[0].equals("deadline")) {
                    String event = command.substring(9, command.lastIndexOf('/'));
                    String date = command.substring(command.lastIndexOf('/') + 4);
                    task = new Deadline(event, date);
                    taskList.add(task);
                } else if (comd[0].equals("event")) {
                    String event = command.substring(6, command.lastIndexOf('/'));
                    String date = command.substring(command.lastIndexOf('/') + 4);
                    task = new Event(event, date);
                    taskList.add(task);
                }
                System.out.print(lines);
                System.out.println(indent + " Got it. I've added this task: \n" +
                        indent + "   " + task + "\n" +
                        indent + " Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(lines);
            }
            command = sc.nextLine();
            comd = command.split("\\s");
        }
        System.out.println(lines + indent + " Bye. Hope to see you again soon!\n" + lines);
    }
}
