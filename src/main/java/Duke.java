import java.util.Scanner;

public class Duke {
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void main(String[] args) {
        greet();

        TaskManager taskManager = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskManager.printTasks();
            } else if (input.matches("^done \\d+$")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                taskManager.markAsDone(index);
            } else {
                taskManager.addTask(input);
            }
        }
        scanner.close();
    }
}
