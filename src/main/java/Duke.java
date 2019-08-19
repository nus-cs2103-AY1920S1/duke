import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static Scanner sc;
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static String horizontalLine =
            "    ____________________________________________________________";

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        String input;

        printGreeting();

        while (sc.hasNext()) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                printExit();
                break;
            }

            String[] inputArr = input.split(" ", 2);
            String command = inputArr[0];
            Task task;
            String[] detailsArr;

            try {
                switch (command) {
                case "list":
                    printTasks();
                    break;
                case "done":
                    handleDone(Integer.parseInt(inputArr[1]) - 1);
                    break;
                case "todo":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    task = new Todo(inputArr[1]);
                    handleAddTask(task);
                    break;
                case "deadline":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                    detailsArr = inputArr[1].split(" /by ");
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Please specify the deadline with /by.");
                    }

                    task = new Deadline(detailsArr[0], detailsArr[1]);
                    handleAddTask(task);
                    break;
                case "event":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }

                    detailsArr = inputArr[1].split(" /at ");
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Please specify when the event is on with /at.");
                    }

                    task = new Event(detailsArr[0], detailsArr[1]);
                    handleAddTask(task);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
    }

    private static void printGreeting() {
        // Greet
        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println();
    }

    private static void printTasks() {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");

        int id = 1;
        for (Task task : tasks) {
            System.out.println("     " + id + ". " + task);
            id++;
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    private static void printExit() {
        // Exit
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    private static void handleDone(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("Task not found!");
        }

        Task task = tasks.get(taskIndex);
        task.markAsDone();

        System.out.println(horizontalLine);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(horizontalLine);
        System.out.println();
    }

    private static void handleAddTask(Task task) {
        tasks.add(task);

        System.out.println(horizontalLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + tasks.size()
                + " tasks in the list.");
        System.out.println(horizontalLine);
        System.out.println();
    }
}
