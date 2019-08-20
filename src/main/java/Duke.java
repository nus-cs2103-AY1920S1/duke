import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "     ";
    private static final String separator = "    ____________________________________________________________\n";

    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
        final String endingSentence = "Bye. Hope to see you again soon!";

        System.out.println(showFormattedStr(welcomeSentence));

        String text = scanner.next();
        Command command;

        while (!text.substring(0, 3).equals("bye")) {
            try {
                command = readCommand(text);
                conductCommand(command);
            } catch (DukeException de) {
                System.out.println(showFormattedStr(de.getMessage()));
            }
            text = scanner.next();
        }

        System.out.println(showFormattedStr(endingSentence));
    }

    private static String showFormattedStr(String str) {
        return separator + indentation + str.replace("\n", "\n" + indentation) + "\n" + separator;
    }

    private static String showFormattedList(List<Task> list) {
        String formattedList = separator;
        formattedList = formattedList + indentation + "Here are the tasks in your list:\n";
        for (int i = 1; i <= list.size(); i++) {
            formattedList = formattedList + indentation + i + ". " + list.get(i - 1) + "\n";
        }
        formattedList = formattedList + separator;
        return formattedList;
    }

    private static Command readCommand(String text) throws DukeException {
        Command command;

        try {
            command = Command.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }

    private static void conductCommand(Command command) throws DukeException{
        if (command == Command.LIST) {
            System.out.println(showFormattedList(tasks));
        } else if (command == Command.DONE) {
            Task removedTask = tasks.remove(scanner.nextInt() - 1);
            removedTask.markDone();
            System.out.println(showFormattedStr("Nice! I've marked this task as done:\n" + "  " + removedTask));
        } else {
            Task newTask;
            String description = scanner.nextLine();
            if (command == Command.TODO) {
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                newTask = new Todo(description);
            } else {
                String[] details;
                if (command == Command.DEADLINE) {
                    details = description.split(" /by ");
                    if (details.length != 2) {
                        throw new DukeException ("\"☹ OOPS!!! The description of a deadline is not enough.");
                    }
                    newTask = new Deadline(details[0], details[1]);
                } else {
                    details = description.split(" /at ");
                    if (details.length != 2) {
                        throw new DukeException ("\"☹ OOPS!!! The description of a event is not enough.");
                    }
                    newTask = new Event(details[0], details[1]);
                }
            }
            tasks.add(newTask);
            System.out.println(showFormattedStr(
                    "Got it. I've added this task:\n" + "  " + newTask
                            + "\nNow you have " + tasks.size() + " tasks in the list."));
        }

    }
}
