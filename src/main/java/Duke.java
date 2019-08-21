import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();
        List<Task> tasks = new ArrayList<Task>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int index = 1;
                for (Task task : tasks) {
                    System.out.println(index + ".[" + task.getStatusIcon() + "] " + task.description);
                    index++;
                }
            } else if (input.substring(0, 4).equals("done")) {
                int do_Index = Integer.parseInt(input.substring(5)) - 1;
                Task chosen_Task = tasks.get(do_Index);
                chosen_Task.markAsDone();

                System.out.println("Nice! I've marked this task as done:\n" +
                        "[" + chosen_Task.getStatusIcon() + "] " + tasks.get(do_Index).description);
            } else {
                Task c = new Task(input);
                tasks.add(c);
                System.out.println("added: " + input);
            }
            input = scn.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
