import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();

        final String welcome = "Hello! I'm Duke. What can I do for you?";
        final String farewell = "Bye. Hope to see you again soon!";

        print(welcome);
        prompt();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                process(input, storage);
            } catch (DukeException e) {
                print(String.format("☹ OOPS!!! %s", e.getMessage()));
            } catch (Exception e) {
                print("☹ OOPS!!! An unknown error occurred.");
            }

            prompt();
        }

        print(farewell);
        sc.close();
    }

    private static void process(String input, Storage storage) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];

        switch (command) {
            case "list": {
                ArrayList<Task> items = storage.getTasks();

                StringBuilder output = new StringBuilder();
                output.append("Here are the tasks in your list:\n");
                for (int i = 0; i < items.size(); i++) {
                    output.append(String.format("%d.%s%n", i + 1, items.get(i).toString()));
                }
                print(output.toString().trim());

                break;
            }

            case "done":
            case "delete": {
                if (tokens.length <= 1) {
                    throw new DukeException("Missing task ID.");
                }

                Task task;
                int taskId;
                try {
                    taskId = Integer.parseInt(tokens[1]) - 1;

                    if (command.equals("done")) {
                        task = storage.getTasks().get(taskId);
                        task.markAsDone();
                    } else {
                        task = storage.getTasks().remove(taskId);
                    }
                } catch (Exception e) {
                    throw new DukeException("Invalid task ID.");
                }

                if (command.equals("done")) {
                    print(String.format("Nice! I've marked this task as done:%n%s", task.toString()));
                } else {
                    print(String.format(
                        "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.",
                        task.toString(), storage.getTaskCount()
                    ));
                }

                break;
            }

            case "todo":
            case "deadline":
            case "event": {
                if (tokens.length <= 1) {
                    throw new DukeException("The description of a task cannot be empty.");
                }
                String description = tokens[1];

                Task newTask;

                if (command.equals("todo")) {
                    newTask = new Todo(description);
                } else if (command.equals("deadline")) {
                    if (!description.contains(" /by ")) {
                        throw new DukeException("A deadline must have a date.");
                    }

                    String[] deadlineTokens = description.split(" /by ", 2);
                    newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                } else {
                    if (!description.contains(" /at ")) {
                        throw new DukeException("An event must have a date.");
                    }

                    String[] eventTokens = description.split(" /at ", 2);
                    newTask = new Event(eventTokens[0], eventTokens[1]);
                }

                if (storage.addTask(newTask)) {
                    print(String.format(
                        "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.",
                        newTask.toString(), storage.getTaskCount()
                    ));
                } else {
                    throw new DukeException("Task could not be saved.");
                }

                break;
            }

            default: {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void prompt() {
        System.out.print("> ");
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
