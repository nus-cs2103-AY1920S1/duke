import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static String divider = "\t____________________________________________________________";
    static String intro = "\tHello! I'm Duke\n\tWhat can I do for you?";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println(intro);
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        ArrayList<Task> list = new ArrayList<>(100);

        while (run) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                run = false;
                inputread = "\tBye. Hope to see you again soon!";
            } else if (command.equals("list")) {
                System.out.println(divider);
                System.out.println("\tHere are the tasks in your list:");
                for (Task t : list) {
                    System.out.println("\t" + t);
                }
                System.out.println(divider);
                continue;
            } else if (command.equals("done")) {
                int id = Integer.parseInt(input.split(" ")[1]);
                list.get(id - 1).markAsComplete();

                System.out.println(divider);
                System.out.println("\tNice, I've marked this task as done!");
                System.out.println("\t\t" + list.get(id-1).toStringNoID());
                System.out.println(divider);
                continue;
            } else {
                list.add(new Task(input));
                input = "\tadded: " + input;
            }

            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
        }
    }
}
