import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String indent = "    ";
        String line = "____________________________________________________________";
        String greeting = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        String bye = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            String command = sc.next();

            if (command.equals("done")) {
                int index = Integer.valueOf(sc.next()) - 1;
                Task t = taskList.get(index);
                t.markAsDone();
                System.out.println(indent + line);
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       [" + t.getStatusIcon() + "] " + t.getDescription());
                System.out.println(indent + line + "\n");
            } else if (command.equals("bye")) {
                System.out.println(bye);
            } else if (command.equals("list")) {
                int counter = 1;
                System.out.println(indent + line);
                System.out.println("     Here are the tasks in your list:");
                for (Task t: taskList) {
                    System.out.println(indent + " " + counter + "." + t) ;
                    counter++;
                }
                System.out.println(indent + line + "\n");
            } else {
                Task t;
                String words = sc.nextLine();

                if (command.equals("todo")) {
                    t = new Todo(words.trim());
                } else if (command.equals("event")) {
                    String[] details = words.split(" /at ");
                    t = new Event(details[0].trim(), details[1].trim());
                } else {
                    String[] details = words.split(" /by ");
                    t = new Deadline(details[0].trim(), details[1].trim());
                }
                taskList.add(t);
                System.out.println(indent + line);
                System.out.println("     Got it. I've added this task:");
                System.out.println(indent + "  " + t);
                System.out.println(indent + " Now you have "  + taskList.size() + " tasks in the list.");
                System.out.println(indent + line + "\n");
            }
        }
        sc.close();

    }
}
