import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<Task> listOfTasks = new ArrayList<>();
    protected static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        boolean isRunning = true;
        while (isRunning && sc.hasNext()) {
            String input = sc.next();

            try {
                switch (input) {
                case "todo":
                    handleTodo(sc.nextLine());
                    break;
                case "deadline":
                    handleDeadline(sc.nextLine());
                    break;
                case "event":
                    handleEvent(sc.nextLine());
                    break;
                case "list":
                    handleList();
                    break;
                case "done":
                    handleDone(sc.nextInt());
                    break;
                case "bye":
                    isRunning = false;
                    break;
                default:
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        int index = 1;
        for (Task task : listOfTasks) {
            System.out.printf("%d.%s\n", index, task);
            index++;
        }
    }

    private static void handleTodo(String input) {
        Task todo = new Todo(input);
        listOfTasks.add(todo);
        echoTaskAdded(todo);
    }

    private static void handleDeadline(String input) throws DukeException {
        try {
            String[] strings = input.split(" /by ");
            Task deadline = new Deadline(strings[0], strings[1]);
            listOfTasks.add(deadline);
            echoTaskAdded(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops! Please write in this format: deadline <description> /by <datetime>");
        }
    }

    private static void handleEvent(String input) throws DukeException {
        try {
            String[] strings = input.split(" /at ");
            Task event = new Event(strings[0], strings[1]);
            listOfTasks.add(event);
            echoTaskAdded(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops! Please write in this format: event <description> /at <datetime>");
        }
    }

    private static void echoTaskAdded(Task output) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("%s\n", output);
        System.out.printf("Now you have %d tasks in the list.\n", listOfTasks.size());
    }

    private static void handleDone(int input) throws DukeException {
        try {
            Task taskDone = listOfTasks.get(input - 1);
            taskDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("%s\n", taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Your task cannot be found!");
        }
    }
}
