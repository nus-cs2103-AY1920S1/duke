import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String indentation = "     ";
    private static final String separator = "    ____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
        final String endingSentence = "Bye. Hope to see you again soon!";
        final ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println(showFormattedStr(welcomeSentence));
        Command command = readCommand(scanner.next().toUpperCase());
        String description;
        Task newTask;
        String[] detail;

        while (!command.equals(Command.BYE)) {
            switch (command) {
                case TODO:
                    description = scanner.nextLine();
                    newTask = new Todo(description);
                    tasks.add(newTask);
                    System.out.println(showFormattedStr(
                            "Got it. I've added this task:\n" + "  " + newTask
                            + "\nNow you have " + tasks.size() + " tasks in the list."));
                    break;
                case DEADLINE:
                    description = scanner.nextLine();
                    detail = description.split(" /by ");
                    newTask = new Deadline(detail[0], detail[1]);
                    tasks.add(newTask);
                    System.out.println(showFormattedStr(
                            "Got it. I've added this task:\n" + "  " + newTask
                           + "\nNow you have " + tasks.size() + " tasks in the list."));
                    break;
                case EVENT:
                    description = scanner.nextLine();
                    detail = description.split(" /at ");
                    newTask = new Event(detail[0], detail[1]);
                    tasks.add(newTask);
                    System.out.println(showFormattedStr(
                            "Got it. I've added this task:\n" + "  " + newTask
                                    + "\nNow you have " + tasks.size() + " tasks in the list."));
                    break;
                case LIST:
                    System.out.println(showFormattedList(tasks));
                    break;
                case DONE:
                    Task removedTask = tasks.remove(scanner.nextInt() - 1);
                    removedTask.markDone();
                    System.out.println(showFormattedStr("Nice! I've marked this task as done:\n" + "  " + removedTask));
                    break;
                default:
                    break;
            }
            command = readCommand(scanner.next());
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
            formattedList = formattedList + indentation + i + ". " + list.get(i-1) + "\n";
        }
        formattedList = formattedList + separator;
        return formattedList;
    }

    private static Command readCommand(String command) {

        return Command.valueOf(command.toUpperCase());
    }
}
