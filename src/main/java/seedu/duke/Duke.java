package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /**
     * Creates the Duke Logo.
     * @params args String[]
     */
    public static void main(String[] args) {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        ArrayList<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String cmd = "";
        Scanner sc = new Scanner(System.in);
        cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            System.out.println(line);
            if (cmd.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else if (cmd.contains("done")) {
                String[] words = cmd.split(" ");
                System.out.println("Nice! I've marked this task as done: ");
                Task taskToMarkAsDone = tasks.get(Integer.parseInt(words[1]) - 1);
                taskToMarkAsDone.markAsDone();
                System.out.println(taskToMarkAsDone);

            } else {
                Task newTask = new Task(cmd);
                tasks.add(newTask);
                System.out.println("added: " + cmd);
            }
            System.out.println(line);
            cmd = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

    }


}
