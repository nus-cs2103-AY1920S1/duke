import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void printAddedTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.print("Now you have " + taskListSize);
        if (taskListSize <= 1) System.out.println(" task in the list.");
        else System.out.println(" tasks in the list.");
    }

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> tasks = new LinkedList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            try {
                String[] command = userInput.split(" ", 2);
                switch (command[0]) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    case "done":
                        System.out.println("Nice! I've marked this task as done:");
                        int i = Integer.parseInt(command[1]) - 1;
                        Task t = tasks.remove(i);
                        t.markAsDone();
                        tasks.add(i, t);
                        System.out.println(t);
                        break;
                    case "todo":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                        Task todo = new Todo(command[1]);
                        tasks.add(todo);
                        printAddedTask(todo, tasks.size());
                        break;
                    case "deadline":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                        String[] detailsD = command[1].split(" /by ");
                        if (detailsD.length < 2)
                            throw new DukeException("\u2639 OOPS!!! Deadline missing date and time.");
                        Task deadline = new Deadline(detailsD[0], detailsD[1]);
                        tasks.add(deadline);
                        printAddedTask(deadline, tasks.size());
                        break;
                    case "event":
                        if (command.length < 2)
                            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
                        String[] detailsE = command[1].split(" /at ");
                        if (detailsE.length < 2)
                            throw new DukeException("\u2639 OOPS!!! Event missing start and end time.");
                        Task event = new Event(detailsE[0], detailsE[1]);
                        tasks.add(event);
                        printAddedTask(event, tasks.size());
                        break;
                    default:
                        throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("\u2639 OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } finally {
                userInput = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
