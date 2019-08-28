import java.util.Scanner;

public class Duke {
    private static final Task[] tasks = new Task[100];
    private static int numOfTasks;

    private static void handleList() {
        if (numOfTasks == 0) {
            System.out.println("     There are no tasks for now!");
            return;
        }
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("     %d.%s\n", i + 1, tasks[i]);
        }
    }

    private static void handleDone(String line) throws DukeException {
        try {
            String[] input = line.split(" ");
            Task task = tasks[Integer.parseInt(input[1]) - 1];
            task.markAsDone();
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.printf("       %s\n", task);
        } catch (Exception e) {
            throw new DukeException("Oops! Please enter a valid task number.");
        }
    }

    private static void handleToDo(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        for (int i = 1; i < input.length; i++) {
            description = description.concat(" " + input[i]);
        }
        description = description.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        Task task = new Todo(description);
        tasks[numOfTasks] = task;
        numOfTasks++;
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", numOfTasks);
    }

    private static void handleDeadLine(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        String by = "";
        boolean isDescription = true;
        for (String word: input) {
            if (word.equals("deadline")) continue;
            else if (word.equals("/by")) isDescription = false;
            else if (isDescription) description = description.concat(" " + word);
            else by = by.concat(" " + word);
        }
        description = description.trim();
        by = by.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        if (by.isBlank()) {
            throw new DukeException("Oops! The task deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        tasks[numOfTasks] = task;
        numOfTasks++;
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", numOfTasks);
    }

    private static void handleEvent(String line) throws DukeException {
        String[] input = line.split(" ");
        String description = "";
        String at = "";
        boolean isDescription = true;
        for (String word: input) {
            if (word.equals("event")) continue;
            else if (word.equals("/at")) isDescription = false;
            else if (isDescription) description = description.concat(" " + word);
            else at = at.concat(" " + word);
        }
        description = description.trim();
        at = at.trim();
        if (description.isBlank()) {
            throw new DukeException("Oops! The task description cannot be empty.");
        }
        if (at.isBlank()) {
            throw new DukeException("Oops! The task dates/times cannot be empty.");
        }
        Task task = new Event(description, at);
        tasks[numOfTasks] = task;
        numOfTasks++;
        System.out.println("     Got it. I've added this task: ");
        System.out.printf("       %s\n", task);
        System.out.printf("     Now you have %d tasks in the list.\n", numOfTasks);
    }

    private static void handleException() {
        System.out.println("     Oops! I'm sorry. I don't know what that means.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }
            if (line.equals("list")) {
                handleList();
                continue;
            }
            String[] input = line.split(" ");
            try {
                switch (input[0]) {
                    case "done":
                        handleDone(line);
                        break;
                    case "todo":
                        handleToDo(line);
                        break;
                    case "deadline":
                        handleDeadLine(line);
                        break;
                    case "event":
                        handleEvent(line);
                        break;
                    default:
                        throw new DukeException("Oops! I don't understand your commmand.");
                }
            } catch (DukeException e) {
                System.out.println("     " + e.getMessage());
            }
        }
    }
}
