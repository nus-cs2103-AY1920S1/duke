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
            }

            switch (command) {
                case "list":
                    String task = "";
                    // print all tasks in list
                    System.out.println("Here are the task(s) in your list:");
                    for (int i = 0; i < num_tasks; i++) {
                        task = String.format("%d.%s", (i + 1), tasks[i]);
                        System.out.println(task);
                    }
                    break;
                case "done":
                    int task_number = Integer.parseInt(input.split(" ")[1]);
                    tasks[task_number - 1].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", tasks[task_number - 1]));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    String task_description = input.split(" ", 2)[1];
                    AddTask(command, task_description, tasks, num_tasks);
                    num_tasks++;
                    break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void AddTask(String type, String description, Task[] tasks, int num_tasks) {
        Task new_task = null;

        switch (type) {
            case "todo":
                new_task = new ToDo(description);
                break;
            case "deadline":
                String[] descriptionDeadline = description.split(" /by ", 2);
                new_task = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
                break;
            case "event":
                String[] descriptionTime = description.split(" /at ", 2);
                new_task = new Event(descriptionTime[0], descriptionTime[1]);
                break;
        }

        tasks[num_tasks] = new_task;
        String message = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                new_task, num_tasks + 1
        );
        System.out.println(message);
    }
}
