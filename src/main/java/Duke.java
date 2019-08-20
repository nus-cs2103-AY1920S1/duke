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
            String str = sc.nextLine();

            if (str.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\n\t" + (i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println("\t____________________________________________________________\n");
            } else if (str.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n");
                break;
            } else {
                // Create task
                Task t = new Task(str);
                taskList.add(t);
                System.out.println("\t____________________________________________________________");
                System.out.println("\n\t" + "added: " + str);
                System.out.println("\t____________________________________________________________\n");
            }
        }

        sc.close();

    }
}
