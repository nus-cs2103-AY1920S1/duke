import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            try {
                run(input);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println();
        }
    }

    private static void run(String input) throws DukeException {
        if (input.equals("list")) {
            showTasks();
        } else if (input.matches("^done\\s+\\d+$")) {
            int taskId = Integer.parseInt(input.split("\\s+")[1]);
            completeTask(taskId);
        } else if (input.matches("^(todo|deadline|event).*")) {
            addTask(input);
        } else if (input.matches("^delete\\s+\\d+$")) {
            int taskId = Integer.parseInt(input.split("\\s+")[1]);
            deleteTask(taskId);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    private static void completeTask(int taskId) throws DukeException {
        try {
            Task task = list.get(taskId - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format(
                    "Task No.%d is not present in the list. Please enter a valid task ID.", taskId));
        }
    }

    private static void addTask(String input) throws DukeException {
        String[] inputBreakDown = input.trim().split("\\s+", 2);
        String taskType = inputBreakDown[0];

        Task newTask;

        switch(taskType) {
            case "todo":
                if (inputBreakDown.length < 2 ) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }

                newTask = new Todo(inputBreakDown[1]);
                break;
            case "deadline":
                if (inputBreakDown.length < 2 || inputBreakDown[1].equals("/by")) {
                    throw new DukeException("The description and the due time of a deadline cannot be empty.");
                }
                if (inputBreakDown[1].indexOf("/by") == 0) {
                    throw new DukeException("The description of a deadline cannot be empty");
                }
                if (!inputBreakDown[1].contains("/by") || inputBreakDown[1].matches(".*/by$")) {
                    throw new DukeException("The due time of a deadline cannot be empty");
                }

                String[] deadlineBreakDown = inputBreakDown[1].split("\\s*/by\\s*", 2);
                newTask = new Deadline(deadlineBreakDown[0], deadlineBreakDown[1]);
                break;
            case "event":
                if (inputBreakDown.length < 2 || inputBreakDown[1].equals("/at")) {
                    throw new DukeException("The description and the time of an event cannot be empty.");
                }
                if (inputBreakDown[1].indexOf("/at") == 0) {
                    throw new DukeException("The description of an event cannot be empty");
                }
                if (!inputBreakDown[1].contains("/at") || inputBreakDown[1].matches(".*/at$")) {
                    throw new DukeException("The time of an event cannot be empty");
                }

                String[] eventBreakDown = inputBreakDown[1].split("\\s*/at\\s*", 2);
                newTask = new Event(eventBreakDown[0], eventBreakDown[1]);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        int total = list.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }

    private static void deleteTask(int taskId) {
        try {
            Task task = list.get(taskId - 1);
            list.remove(task);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + task);
            int total = list.size();
            System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format(
                    "Task No.%d is not present in the list. Please enter a valid task ID.", taskId));
        }
    }
}
