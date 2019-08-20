import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String input;
    private static boolean running;
    private static List<Task> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        setupResources();
        greet();
        while (running) {
            readInput();
            processInput();
        }
    }

    public static void setupResources() {
        running = true;
        list = new ArrayList<>();
    }

    public static void greet() {
        running = true;
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hello! I'm Duke\n" +
                        "     What can I do for you?\n" +
                        "    ____________________________________________________________\n");
    }

    public static void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________"
        );
        running = false;
    }

    public static void addToList() {
        Task toAdd = new Task(input);
        list.add(toAdd);
        System.out.println(
                "    ____________________________________________________________\n" +
                        String.format("     added: %s\n", toAdd) +
                        "    ____________________________________________________________\n"
        );
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("     %d. %s", i+1, list.get(i)));
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void readInput() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    public static void markAsDone(int positionInList) {
        Task taskToMark = list.get(positionInList - 1);
        taskToMark.done();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("     %s", taskToMark));
        System.out.println("    ____________________________________________________________");
    }

    public static void processInput() {
        Scanner scanner = new Scanner(input);
        String toProcess = scanner.next();

        switch (toProcess) {
            case "list":
                printList();
                break;

            case "bye":
                sayGoodbye();
                break;

            case "done":
                markAsDone(scanner.nextInt());
                break;

            default:
                addToList();
        }
    }
}
