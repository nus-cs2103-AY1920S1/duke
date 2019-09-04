import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main method of Duke application.
     */
    public static void main(String[] args) {
        // Create a scanner to take in user input
        Scanner sc = new Scanner(System.in);

        System.out.println(
                formatMessage("Hello, I'm Duke\nWhat can I do for you?")
        );

        String command = sc.nextLine();
        String[] commandTokens = command.split(" ");
        ArrayList<Task> tasks = new ArrayList<>();
        String message;

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    String list = "Here are the tasks in your list:\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        list += String.format(
                                "%d." + task,
                                i + 1
                        );
                    }
                    System.out.println(formatMessage(list));
                } else if (commandTokens[0].equals("done")) {
                    int completedIndex = Integer.parseInt(commandTokens[1]) - 1;
                    message = "Nice! I've marked this task as done:\n  ";
                    Task completedTask = tasks.get(completedIndex);
                    completedTask.setCompleted();
                    message += completedTask;
                    System.out.println(formatMessage(message));
                } else if (commandTokens[0].equals("todo")) {
                    if (commandTokens.length == 1) {
                        throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(command.substring(5));
                    tasks.add(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.size()
                    );
                    System.out.println(
                            formatMessage(message)
                    );
                } else if (commandTokens[0].equals("deadline")) {
                    String[] deadlineTokens = command.substring(9).split(" /by ");
                    Task newTask = new Deadline(deadlineTokens[0], deadlineTokens[1]);
                    tasks.add(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.size()
                    );
                    System.out.println(
                            formatMessage(message)
                    );
                } else if (commandTokens[0].equals("event")) {
                    String[] eventTokens = command.substring(6).split(" /at ");
                    Task newTask = new Event(eventTokens[0], eventTokens[1]);
                    tasks.add(newTask);
                    message = String.format(
                            "Got it. I've added this task:\n  %sNow you have %d tasks in the list.",
                            newTask,
                            tasks.size()
                    );
                    System.out.println(
                            formatMessage(message)
                    );
                } else if (commandTokens[0].equals("delete")) {
                    int removeIndex = Integer.parseInt(commandTokens[1]) - 1;
                    Task removeTask = tasks.get(removeIndex);
                    tasks.remove(removeIndex);
                    message = String.format(
                            "Noted. I've removed this task:\n  %sNow you have %d tasks in the list.",
                            removeTask,
                            tasks.size());
                    System.out.println(formatMessage(message));
                } else {
                    throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(formatMessage(e.getMessage()));
            }
            command = sc.nextLine();
            commandTokens = command.split(" ");
        }

        System.out.println(
                formatMessage("Bye. Hope to see you again soon!")
        );
    }

    private static String formatMessage(String message) {
        String formatted = "    ____________________________________________________________\n";
        String[] lines = message.split("\n");
        for (String line : lines) {
            formatted += "     " + line + "\n";
        }
        formatted += "    ____________________________________________________________\n";

        return formatted;
    }
}
