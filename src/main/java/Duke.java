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
                    handleTodo();
                    break;
                case "deadline":
                    handleDeadline();
                    break;
                case "event":
                    handleEvent();
                    break;
                case "list":
                    handleList();
                    break;
                case "done":
                    handleDone();
                    break;
                case "bye":
                    isRunning = false;
                    break;
                default:
                    throw new DukeException("Oops! You entered an invalid command.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();

        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() throws DukeException {
        if (listOfTasks.isEmpty()) {
            throw new DukeException("Oops! You have no tasks yet.");
        }
        int index = 1;
        for (Task task : listOfTasks) {
            System.out.printf("%d.%s\n", index, task);
            index++;
        }
    }

    private static void handleTodo() throws DukeException {
        try {
            String input = sc.nextLine().trim();
            if (input.isBlank()) {
                throw new IllegalArgumentException();
            }

            Task todo = new Todo(input);
            listOfTasks.add(todo);
            echoTaskAdded(todo);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Oops! Todo task description cannot be blank.");
        } catch (Exception e) {
            throw new DukeException("Oops! Please write in this format: todo <description>");
        }
    }

    private static void handleDeadline() throws DukeException {
        try {
            String input = sc.nextLine();
            String[] strings = input.split("/by");
            String desc = strings[0].trim();
            String by = strings[1].trim();

            if (desc.isBlank() || by.isBlank()) {
                throw new IllegalArgumentException();
            }

            Task deadline = new Deadline(desc, by);
            listOfTasks.add(deadline);
            echoTaskAdded(deadline);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Oops! Deadline task description or deadline cannot be blank.");
        } catch (Exception e) {
            throw new DukeException("Oops! Please write in this format: deadline <description> /by <datetime>");
        }
    }

    private static void handleEvent() throws DukeException {
        try {
            String input = sc.nextLine();
            String[] strings = input.split("/at");
            String desc = strings[0].trim();
            String at = strings[1].trim();

            if (desc.isBlank() || at.isBlank()) {
                throw new IllegalArgumentException();
            }

            Task event = new Event(desc, at);
            listOfTasks.add(event);
            echoTaskAdded(event);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Oops! Deadline task description or deadline cannot be blank.");
        } catch (Exception e) {
            throw new DukeException("Oops! Please write in this format: event <description> /at <datetime>");
        }
    }

    private static void echoTaskAdded(Task output) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("%s\n", output);
        System.out.printf("Now you have %d tasks in the list.\n", listOfTasks.size());
    }

    private static void handleDone() throws DukeException {
        try {
            int input = Integer.parseInt(sc.nextLine().trim());
            Task taskDone = listOfTasks.get(input - 1);
            taskDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("%s\n", taskDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! Your task cannot be found!");
        } catch (Exception e) {
            throw new DukeException("Oops! Please write in this format: done <number>");
        }
    }
}
