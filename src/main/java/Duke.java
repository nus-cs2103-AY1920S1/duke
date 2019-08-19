import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horizontalLine =
            "    ____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printGreeting();

        while (sc.hasNext()) {
            input = sc.nextLine();
            String[] inputWordArr = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printTasks(tasks);
            } else if (inputWordArr[0].equals("done")) {
                Task task = tasks.get(Integer.parseInt(inputWordArr[1]) - 1);
                task.markAsDone();
                System.out.println(horizontalLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + task);
                System.out.println(horizontalLine);
            } else {
                tasks.add(new Task(input));

                System.out.println(horizontalLine);
                System.out.println("     added: " + input);
                System.out.println(horizontalLine);
                System.out.println();
            }
        }

        printExit();
    }

    private static void printGreeting() {
        // Greet
        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println();
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println(horizontalLine);

        int id = 1;
        for (Task task : tasks) {
            System.out.println("     " + id + ". " + task);
            id++;
        }

        System.out.println(horizontalLine);
    }

    private static void printExit() {
        // Exit
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
