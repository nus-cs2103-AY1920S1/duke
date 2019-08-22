import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<Task>();
        initDuke();
    }

    /**
     * Creates the Duke Logo.
     * @params args String[]
     */
    public static void main(String[] args) {

        Duke duke = new Duke();
        String cmd = "";
        Scanner sc = new Scanner(System.in);

        cmd = sc.nextLine();

        while (!cmd.equals("bye")) {
            String[] cmdList = cmd.split(" ");
            String keyword = cmdList[0];
            printLine();

            try {

                if (keyword.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= duke.tasks.size(); i++) {
                        System.out.println(i + "." + duke.tasks.get(i - 1));
                    }

                } else if (keyword.equals("done")) {
                    System.out.println("Nice! I've marked this task as done:");
                    Task taskToMarkAsDone = duke.tasks.get(Integer.parseInt(cmdList[1]) - 1);
                    taskToMarkAsDone.markAsDone();
                    System.out.println(taskToMarkAsDone);

                } else if (keyword.equals("delete")) {
                    Task taskToBeRemoved = duke.tasks.get(Integer.parseInt(cmdList[1]) - 1);
                    duke.tasks.remove(taskToBeRemoved);
                    System.out.println("Noted. I've removed this task:");
                    System.out.print("  ");
                    System.out.println(taskToBeRemoved);
                    System.out.println("Now you have " + duke.tasks.size() + " in the list.");

                } else { // it is a new Task
                    Task newTaskToBeAdded = duke.handleNewTask(keyword, cmd);
                    duke.tasks.add(newTaskToBeAdded);
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    System.out.println(newTaskToBeAdded);
                    System.out.println("Now you have " + duke.tasks.size() + " tasks in the list.");
                }

            } catch (InputMismatchException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
            printLine();
            cmd = sc.nextLine();
        }

        closeDuke();

    }

    private static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private static void initDuke() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    private static void closeDuke() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private Task handleNewTask(String keyword, String cmd) throws InputMismatchException {
        if (keyword.equals("deadline")) {
            String descriptionAndTime = cmd.substring(8);
            String[] details = descriptionAndTime.trim().split(" /by");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of a deadline cannot be empty.");
            }
            return new Deadline(details[0], details[1]);

        } else if (keyword.equals("event")) {
            String descriptionAndTime = cmd.substring(5);
            String[] details = descriptionAndTime.trim().split(" /at");
            if (descriptionAndTime.isEmpty() || details.length <= 1) {
                throw new InputMismatchException("The description of an event cannot be empty.");
            }
            return new Event(details[0], details[1]);

        } else if (keyword.equals("todo")) {
            String description = cmd.substring(4); //words after todo
            if (description.isEmpty()) {
                throw new InputMismatchException("The description of a todo cannot be empty.");
            }
            return new Todo(description);
        } else {
            throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
