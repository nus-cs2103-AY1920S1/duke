import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (command.startsWith("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                tasks.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(index - 1).toString());
            } else {
                String type = command.split(" ", 2)[0];
                String description = command.split(" ", 2)[1];
                Task task;

                if (type.equals("todo")) {
                    task = new Todo(description);
                } else if (command.startsWith("event")) {
                    String at = description.split(" /at ", 2)[1];
                    description = description.split(" /at ", 2)[0];
                    task = new Event(description, at);
                } else {
                    String by = description.split(" /by ", 2)[1];
                    description = description.split(" /by ", 2)[0];
                    task = new Deadline(description, by);
                }

                tasks.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task.toString());
            }

            System.out.println();
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
