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


        String farewellText = "Bye <3 Hope to see you again soon!";

        System.out.println(greetingText);

        while (sc.hasNext()) {
            String input = sc.next();

            try {
                Command command = Command.valueOf(input.toUpperCase());
                switch (command) {
                case BYE:
                    System.out.println(farewellText);
                    return;
                case LIST:
                    System.out.println(listText);
                    listEntries();
                    break;
                case TODO:
                    addTodo(sc);
                    break;
                case DEADLINE:
                    addDeadline(sc);
                    break;
                case EVENT:
                    addEvent(sc);
                    break;
                case DONE:
                    taskDone(sc);
                    break;
                case DELETE:
                    deleteTask(sc);
                    break;
                default:
                    System.out.println("☹  OOPS!!! I'm sorry, but I don't know what that means. "
                            + "I sure need more sleep...");
                    break;
                } // End switch
            } catch (IllegalArgumentException e) {
                System.out.println("☹  OOPS!!! I'm sorry, but I don't know what that means. "
                        + "I sure need more sleep...");
            }

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
        try {
            String params = sc.nextLine().trim();
            if (params.isEmpty()) {
                throw new IllegalArgumentException();
            }
            Todo todo = new Todo(params);
            tasks.add(todo);
            addTaskDialogue(todo.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(":( OOPS!!! The description of a todo cannot be empty.");
        } catch (Exception e) {
            System.out.println("To enter a new todo, type: todo <description>");
        }
    } // End method.

    private static void addDeadline(Scanner sc) {
        String params = sc.nextLine().trim();
        String[] paramsArr = params.split("/by");

        try {
            if (params.isEmpty() || paramsArr[0].isEmpty() || paramsArr[1].isEmpty()) {
                throw new IllegalArgumentException();
            }

            Deadline deadline = new Deadline(paramsArr[0].trim(), paramsArr[1].trim());
            tasks.add(deadline);
            addTaskDialogue(deadline.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(":( OOPS!!! The description or due date of a deadline cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The description or due date of a deadline cannot be empty! "
                    + "To enter a new deadline, type: deadline <description> /by <date>");
        } // End catch.
    } // End method.

    private static void addEvent(Scanner sc) {
        String params = sc.nextLine().trim();
        String[] paramsArr = params.split("/at");
        try {
            if (params.isEmpty() || paramsArr[0].isEmpty() || paramsArr[1].isEmpty()) {
                throw new IllegalArgumentException();
            }

            Event event = new Event(paramsArr[0].trim(), paramsArr[1].trim());
            tasks.add(event);
            addTaskDialogue(event.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(":( OOPS!!! The description or date and time of an event cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You need to enter an event? "
                    + "To enter a new event, type: event <description> /at <date&time>");
        } // End catch.
    } // End method.

    private static void taskDone(Scanner sc) {
        String doneText = "Nice! I've marked this task as done: ";
        try {
            // Will cause error if user did not give entry number.
            String inputEntry = sc.nextLine().trim();
            int entry = Integer.parseInt(inputEntry) - 1;

            if (inputEntry.isEmpty() || entry < 0) {
                throw new IllegalArgumentException();
            }

            Task task = tasks.get(entry);
            task.markAsDone();
            System.out.println(doneText);
            System.out.println("  " + task.toString());

        } catch (IllegalArgumentException e) {
            System.out.println("You need to specify the task you want to complete!");
        } // End of try-catch.

    } // End of method.

    private static void deleteTask(Scanner sc) {
        String removeText = "Noted. I've removed this task:";
        try {
            String inputEntry = sc.nextLine().trim();
            int entry = Integer.parseInt(inputEntry) - 1;

            if (inputEntry.isEmpty() || entry < 0) {
                throw new IllegalArgumentException();
            }

            Task removedTask = tasks.remove(entry);

            String taskWord = tasks.size() <= 1 ? "task" : "tasks"; // Ensure correct grammar.

            System.out.println(removeText);
            System.out.println("  " + removedTask.toString());
            System.out.printf("Now you have %d %s in the list.\n", tasks.size(), taskWord);

        } catch (IllegalArgumentException e) {
            System.out.println("You need to specify the task you want to delete!");
        } // End of try-catch.

    } // End of method.

    private static void addTaskDialogue(String desc) {
        String addText = "Got it. I've added this task:";
        String taskWord = tasks.size() <= 1 ? "task" : "tasks"; // Ensure correct grammar.
        String numTaskText = String.format("Now you have %d %s in the list.", tasks.size(), taskWord);

        System.out.println(addText);
        System.out.println("  " + desc);
        System.out.println(numTaskText);
    } // End method.
}
