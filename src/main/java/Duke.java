import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    protected static ArrayList<Task> list = new ArrayList<>();
    protected static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        String command;
        printHello();

        while (!( command = sc.next()).equals("bye")) {
            try {
                if (command.equals("list")) {
                    handleListCommand();
                } else if (command.equals("done")) {
                    handleDoneCommand();
                } else if (command.equals("todo")) {
                    handleTodoCommand();
                } else if (command.equals("deadline")) {
                    handleDeadlineCommand();
                } else if (command.equals("event")) {
                    handleEventCommand();
                } else if (command.equals("delete")) {
                    handleDeleteCommand();
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("    _____________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("    _____________________________________\n");
            }
        }
        printBye();
    }
    public static void printHello() {
        System.out.println("    _____________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    _____________________________________\n");
    }

    public static void printBye() {
        System.out.println("    _____________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________");
    }

    public static void printAddTask(Task newTask) {
        System.out.println("    _____________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        System.out.println("    _____________________________________\n");
    }

    public static void handleTodoCommand() throws DukeException {
        try {
            String description = sc.nextLine().trim();
            if (description.isBlank()) {
                throw new IllegalArgumentException();
            }
            Task newTodo = new Todo(description);
            list.add(newTodo);
            printAddTask(newTodo);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: todo [task description]");
        }
    }

    public static void handleListCommand() throws DukeException {
        try {
            String statement = sc.nextLine();

            if (!statement.isBlank()) {
                throw new Exception();
            }

            System.out.println("    _____________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                int number = i + 1;
                System.out.println("     " + number + "." + list.get(i));
            }
            System.out.println("    _____________________________________\n");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: list");
        }
    }

    public static void handleDeadlineCommand() throws DukeException {
        try {
            String[] statement = sc.nextLine().split("/by");
            String taskDescription = statement[0].trim();
            String taskBy = statement[1].trim();

            if (taskDescription.isBlank() || taskBy.isBlank()) {
                throw new IllegalArgumentException();
            }

            Task newDeadline = new Deadline(taskDescription, taskBy);
            list.add(newDeadline);
            printAddTask(newDeadline);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! Task description/Task by can not be empty");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: deadline [task description] /by [task deadline]");
        }
    }

    public static void handleEventCommand() throws DukeException {
        try {
            String[] statement = sc.nextLine().split("/at");
            String taskDescription = statement[0].trim();
            String taskAt = statement[1].trim();

            if (taskDescription.isBlank() || taskAt.isBlank()) {
                throw new IllegalArgumentException();
            }

            Task newEvent = new Event(taskDescription, taskAt);
            list.add(newEvent);
            printAddTask(newEvent);
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! Task description/Task at can not be empty");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: event [task description] /at [task at]");
        }
    }

    public static void handleDoneCommand() throws DukeException {
        try {
            int taskNumber = Integer.parseInt(sc.nextLine().trim()) - 1;
            list.get(taskNumber).markAsDone();
            System.out.println("    _____________________________________");
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + list.get(taskNumber));
            System.out.println("    _____________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number you specified is not in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: done [task number]");
        }
    }

    public static void handleDeleteCommand() throws DukeException {
        try {
            int taskNumber = Integer.parseInt(sc.nextLine().trim()) - 1;
            Task deletedTask = list.get(taskNumber);
            list.remove(taskNumber);
            System.out.println("    _____________________________________");
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + deletedTask);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
            System.out.println("    _____________________________________\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task number you specified is not in the list.");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! Your input format is wrong. Use: delete [task number]");
        }
    }

}
