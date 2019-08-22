import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // initialise items list
        tasks = new ArrayList<>();

        greet();
        getUserInput();
    }

    private static void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String firstWord = userInput.split("\\s")[0];
        switch (firstWord) {
            case "bye":
                String farewellMessage = LINE
                        + "     Bye. Hope to see you again soon!\n"
                        + LINE;
                System.out.print(farewellMessage);
                break;
            case "list":
                System.out.print(LINE);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    System.out.println("     " + Integer.toString(i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask);
                }
                System.out.print(LINE);
                getUserInput();
                break;
            case "done":
                int index = Integer.parseInt(userInput.split("\\s")[1]);
                Task taskDone = tasks.get(index - 1);
                taskDone.markAsDone();
                System.out.print(LINE);
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       [" + taskDone.getStatusIcon() + "] " + taskDone);
                System.out.print(LINE);
                getUserInput();
                break;
            default:
                tasks.add(new Task(userInput));
                System.out.print(LINE + "     added: " + userInput + "\n" + LINE);
                getUserInput();
        }

    }
}
