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
            process(input, storage);

            prompt();
        }

        print(farewell);
        sc.close();
    }

    private static void process(String input, Storage storage) {
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

            case "done": {
                int taskId = Integer.parseInt(tokens[1]) - 1;
                Task task = storage.getTasks().get(taskId);

                task.markAsDone();
                print(String.format("Nice! I've marked this task as done:%n%s", task.toString()));

                break;
            }

            case "todo":
            case "deadline":
            case "event": {
                Task newTask;

                if (command.equals("todo")) {
                    newTask = new Todo(tokens[1]);
                } else if (command.equals("deadline")) {
                    String[] deadlineTokens = tokens[1].split(" /by ", 2);
                    newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                } else {
                    String[] eventTokens = tokens[1].split(" /at ", 2);
                    newTask = new Event(eventTokens[0], eventTokens[1]);
                }

                if (storage.addTask(newTask)) {
                    print(String.format(
                        "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.",
                        newTask.toString(), storage.getTaskCount()
                    ));
                }

                break;
            }

            default: {
                print("I don't understand, please try again.");
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
