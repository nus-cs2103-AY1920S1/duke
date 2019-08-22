import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean readingCommands = true;
        while (readingCommands) {
            try {
                String input = sc.nextLine();
                String[] inputWords = input.split(" ", 2);
                String command = inputWords[0];

                switch (command) {
                    case "todo": {
                        String description = inputWords[1];
                        addTask(new ToDo(description));
                        break;
                    }

                    case "deadline": {
                        String[] params = inputWords[1].split(" /by ");
                        String description = params[0];
                        String by = params[1];
                        addTask(new Deadline(description, by));
                        break;
                    }

                    case "event": {
                        String[] params = inputWords[1].split(" /at ");
                        String description = params[0];
                        String at = params[1];
                        addTask(new Event(description, at));
                        break;
                    }

                    case "list": {
                        for (int i = 1; i <= tasks.size(); i++) {
                            Task task = tasks.get(i - 1);
                            System.out.println(i + ". " + task);
                        }
                        break;
                    }

                    case "done": {
                        int taskId = Integer.parseInt(inputWords[1]) - 1;
                        Task task = tasks.get(taskId);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                        break;
                    }

                    case "bye": {
                        readingCommands = false;
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    }

                    default: {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
}

