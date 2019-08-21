import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "-".repeat(60);

    private static void echo(final String message) {
        System.out.println(separator);
        System.out.println(message.stripTrailing());
        System.out.println(separator);
    }

    public static void main(String[] args) {
        echo("Hello from\n" + logo + "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Tasks tasks = new Tasks();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "list":
                    echo(tasks.toString());
                    break;
                case "bye":
                    echo("Bye. Hope to see you again soon!");
                    return;
                default:
                    Task task = new Task(input);
                    if (tasks.addTask(task)) {
                        echo("added: " + task.getContent());
                    }
                    break;
            }
        }
    }
}
