import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the Duke application object.
 */
public class Duke {
    // Class Variables
    private static List<Task> tasks = new ArrayList<>(100); // Specification said numTasks < 100.

    /**
     * Runs the Duke application.
     * @param args Stdin.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greetingText = "Hello! I'm Duke\nWhat can I do for you?";
        String listText = "Here are the tasks in your list:";
        String doneText = "Nice! I've marked this task as done: ";
        String farewellText = "Bye. Hope to see you again soon!";

        System.out.println(greetingText);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");

            switch (inputArr[0]) {
            case "bye":
                System.out.println(farewellText);
                return;
            case "list":
                System.out.println(listText);
                listEntries();
                break;
            case "done":
                // Will cause error if user did not give entry number.
                int entry = Integer.parseInt(inputArr[1]) - 1;
                Task task = tasks.get(entry);
                task.markAsDone();
                System.out.println(doneText);
                System.out.println("  " + task.printStatus());
                break;
            default:
                tasks.add(new Task(input));
                System.out.println("added: " + input);
                break;
            } // End switch

        } // End while loop.

        sc.close();
    } // End of main.

    /**
     * List all entries recorded by Duke; print nothing if no entries are present.
     */
    protected static void listEntries() {
        int numEntry = 1;
        for (Task task : Duke.tasks) {
            System.out.printf("%d. %s\n", numEntry, task.printStatus());
            numEntry++;
        } // End for loop.
    } // End method.
}
