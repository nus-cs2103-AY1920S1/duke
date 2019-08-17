import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int num_tasks = 0;

        String input = "";
        String command = "";

        while (true) {
            input = sc.nextLine();
            command = input.split(" ")[0];
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                String task = "";
                // print all tasks in list
                for (int i = 0; i < num_tasks; i++) {
                    task = String.format("%d.%s", (i + 1), tasks[i]);
                    System.out.println(task);
                }
            } else if (command.equalsIgnoreCase("done")) {
                int task_number = Integer.parseInt(input.split(" ")[1]);
                tasks[task_number - 1].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s", tasks[task_number - 1]));
            } else {
                // add task to list
                tasks[num_tasks] = new Task(input);
                num_tasks++;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
