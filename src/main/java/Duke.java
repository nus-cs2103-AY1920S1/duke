import java.util.Scanner;

public class Duke {
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        TaskManager.separator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        TaskManager.separator();
    }

    public static void main(String[] args) {
        greet();

        TaskManager taskManager = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                TaskManager.separator();
                System.out.println("Bye. Hope to see you again soon!");
                TaskManager.separator();
                break;
            } else if (input.equals("list")) {
                taskManager.printTasks();
            } else if (input.matches("^done \\d+$")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                taskManager.markAsDone(index);
            } else if (input.startsWith("deadline ")) {
                String suffix = input.split(" ", 2)[1];
                String description = suffix.split(" /by ", 2)[0];
                String by = suffix.split(" /by ", 2)[1];
                taskManager.addTask(new Deadline(description, by));
            } else if  (input.startsWith("todo ")) {
                String description = input.split(" ", 2)[1];
                taskManager.addTask(new Todo(description));
            } else {
                scanner.close();
                throw new IllegalArgumentException();
            }
        }
        scanner.close();
    }
}
