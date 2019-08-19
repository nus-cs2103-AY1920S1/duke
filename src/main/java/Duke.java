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
            String input = sc.next();
            String[] inputArr = input.split(" ");

            switch (input) {
            case "bye":
                System.out.println(farewellText);
                return;
            case "list":
                System.out.println(listText);
                listEntries();
                break;
            case "todo":
                addTodo(sc);
                break;
            case "deadline":
                addDeadline(sc);
                break;
            case "event":
                addEvent(sc);
                break;
            case "done":
                // Will cause error if user did not give entry number.
                int entry = Integer.parseInt(sc.nextLine().trim()) - 1;
                Task task = tasks.get(entry);
                task.markAsDone();
                System.out.println(doneText);
                System.out.println("  " + task.toString());
                break;
            default: // should change ltr
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
    private static void listEntries() {
        int numEntry = 1;
        for (Task task : Duke.tasks) {
            System.out.printf("%d. %s\n", numEntry, task.toString());
            numEntry++;
        } // End for loop.
    } // End method.

    private static void addTodo(Scanner sc) {
        String params = sc.nextLine().trim();
        Todo todo = new Todo(params);
        tasks.add(todo);
        addTaskDialogue(todo.toString());
    } // End method.

    private static void addDeadline(Scanner sc) {
        String params = sc.nextLine().trim();
        String[] paramsArr = params.split("/by");
        try {
            Deadline deadline = new Deadline(paramsArr[0].trim(), paramsArr[1].trim());
            tasks.add(deadline);
            addTaskDialogue(deadline.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        } // End catch.
    } // End method.

    private static void addEvent(Scanner sc) {
        String params = sc.nextLine().trim();
        String[] paramsArr = params.split("/at");
        try {
            Event event = new Event(paramsArr[0].trim(), paramsArr[1].trim());
            tasks.add(event);
            addTaskDialogue(event.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        } // End catch.
    } // End method.

    private static void addTaskDialogue(String desc) {
        String addText = "Got it. I've added this task:";
        String taskWord = tasks.size() <= 1 ? "task" : "tasks"; // Ensure correct grammar.
        String numTaskText = String.format("Now you have %d %s in the list.", tasks.size(), taskWord);

        System.out.println(addText);
        System.out.println("  " + desc);
        System.out.println(numTaskText);
    } // End method.
}
