import java.net.SocketOption;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scanner  = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if (input.equals("list")) {
                    printList(tasks);
                } else if (input.contains("done")) {
                    String[] inputs = input.split(" ");
                    int number = Integer.parseInt(inputs[1]) - 1;
                    tasks.get(number).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(number));
                } else if (input.startsWith("todo")) {
                    if (!input.substring(4).isEmpty()) {
                        String description = input.substring(4);
                        Todo todo = new Todo(description);
                        tasks.add(todo);
                        printout(todo, tasks);
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                } else if (input.startsWith("deadline")) {
                    if (input.contains("/by")) {
                        int index = input.lastIndexOf("/by");
                        String description = input.substring(8, index);
                        String by = input.substring(index + 3);
                        if (description.isBlank()) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        if (by.isBlank()) {
                            throw new InvalidDescriptionException("deadline");
                        }
                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);
                        printout(deadline, tasks);
                    } else {
                        throw new InvalidDescriptionException("deadline");
                    }
                } else if (input.startsWith("event")) {
                    if (input.contains("/at")) {
                        int index = input.lastIndexOf("/at");
                        String description = input.substring(5, index);
                        String at = input.substring(index + 3);
                        if (description.isBlank()) {
                            throw new EmptyDescriptionException("event");
                        }
                        if (at.isBlank()) {
                            throw new InvalidDescriptionException("event");
                        }
                        Event event = new Event(description, at);
                        tasks.add(event);
                        printout(event, tasks);
                    } else {
                        throw new InvalidDescriptionException("event");
                    }
                } else {
                    throw new InvalidInputException();
                }
            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    public static void printout(Task task, ArrayList<Task> tasksList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> tasksList) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + "." + tasksList.get(i));
        }
    }
    public static void handleException(Exception e) {
        if (e instanceof InvalidInputException) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e instanceof EmptyDescriptionException) {
            System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
        } else if (e instanceof InvalidDescriptionException) {
            System.out.println(String.format("OOPS!!! Invalid input! Make sure your %s has a description and required data after /at for Event or /by for Deadline.\n", e.getMessage()));
        } else {
            System.out.println(e.getMessage());
        }

    }
}

