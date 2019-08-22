import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfTasks = 0;

    public Duke() {
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Lists out all the tasks in Duke
    public void list() throws IllegalArgumentException {
        if (tasks.size() == 0) {
            throw new IllegalArgumentException("Nothing found in list");
        }
        int number = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    public void add(Task task) {
        numberOfTasks++;
        tasks.add(task);
        String outputString = "Got it. I've added this task: \n" + task.toString();
        System.out.println(outputString);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void done(int number) throws IndexOutOfBoundsException {
        if (number > Duke.numberOfTasks || number <= 0) {
            throw new IndexOutOfBoundsException("The task number does not exist.");
        }
        Task task = Duke.tasks.get(number - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            try {
                if (command.equalsIgnoreCase("list")) {
                    duke.list();
                } else {
                    String[] commandSplit = command.split(" ");
                    String deadline = "deadline";
                    String event = "event";
                    String todo = "todo";
                    if (commandSplit[0].equalsIgnoreCase("done")) {
                        int index = Integer.parseInt(commandSplit[1]);
                        duke.done(index);
                    } else if (commandSplit[0].equalsIgnoreCase(deadline)) {
                        String details = command.substring(deadline.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a deadline cannot be empty.");
                        }
                        String[] detail = details.split(" /by");
                        Task addTask = new Deadline(detail[0], detail[1]);
                        duke.add(addTask);
                    } else if (commandSplit[0].equalsIgnoreCase(event)) {
                        String details = command.substring(event.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a event cannot be empty.");
                        }
                        String[] detail = details.split(" /at");
                        Task addTask = new Event(detail[0], detail[1]);
                        duke.add(addTask);
                    } else if (commandSplit[0].equalsIgnoreCase(todo)) {
                        String details = command.substring(todo.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a todo cannot be empty.");
                        }
                        Task addTask = new Todo(details);
                        duke.add(addTask);
                    } else {
                        throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
            command = scanner.nextLine();
        }
        duke.bye();
    }
}