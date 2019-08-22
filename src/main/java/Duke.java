import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(formatMessage("Hello, I'm Duke\nWhat can I do for you?"));

        String command = sc.nextLine();
        ArrayList<Task> tasks = new ArrayList<>();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                String list = "Here are the tasks in your list:\n";
                for (int i = 0; i < tasks.size(); i++) {
                    list += String.format(
                            "%d." + tasks.get(i) + "\n",
                            i + 1,
                            tasks.get(i).getMark());
                }
                System.out.println(formatMessage(list));
            } else if (command.length() > 3 && command.substring(0, 4).equals("done")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]);
                tasks.get(taskNo - 1).setDone(true);
                String message = "Nice! I've marked this task as done:\n";
                message += "  " + tasks.get(taskNo - 1) + "\n";
                System.out.println(formatMessage(message));
            } else if (command.length() > 5 && command.substring(0, 6).equals("delete")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]);
                String message = "Noted. I've removed this task:\n";
                message += "  " + tasks.get(taskNo - 1) + "\n";
                message += String.format("Now you have %d tasks in the list.", tasks.size() - 1);
                tasks.remove(taskNo);
                System.out.println(formatMessage(message));
            } else {
                try {
                    String[] commandWithType = command.split(" ", 2);
                    Task newTask = null;
                    if (commandWithType[0].equals("todo")) {
                        if (commandWithType.length == 1) {
                            throw new Error("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        newTask = new Todo(commandWithType[1]);
                        tasks.add(newTask);
                    } else if (commandWithType[0].equals("deadline")) {
                        if (commandWithType.length == 1) {
                            throw new Error("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] deadlineInfo = commandWithType[1].split(" /by ");
                        newTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                        tasks.add(newTask);
                    } else if (commandWithType[0].equals("event")) {
                        if (commandWithType.length == 1) {
                            throw new Error("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] eventInfo = commandWithType[1].split(" /at ");
                        newTask = new Event(eventInfo[0], eventInfo[1]);
                        tasks.add(newTask);
                    } else {
                        throw new Error("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", newTask, tasks.size());
                    System.out.println(formatMessage(message));
                } catch (Error e) {
                    System.out.println(formatMessage(e.getMessage()));
                }
            }
            command = sc.nextLine();
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
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
