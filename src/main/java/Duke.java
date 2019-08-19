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
        String description = scanner.nextLine();
        while (!description.equals("bye")) {
            if (description.equals("list")) {
                System.out.println(showFormattedList(tasks));
            } else {
                tasks.add(new Task(description));
                System.out.println(showFormattedStr("added: " + description));
            }
            description = scanner.nextLine();
        }
        System.out.println(showFormattedStr(endingSentence));
    }

    private static String showFormattedStr(String str) {
        return separator + indentation + str.replace("\n", "\n" + indentation) + "\n" + separator;
    }

    private static String showFormattedList(List<Task> list) {
        String formattedList = separator;
        for (Task task : list) {
            formattedList = formattedList + indentation + task.toString() + "\n";
        }
        formattedList = formattedList + separator;
        return formattedList;
    }

    private static Command readCommand(String command) {
        return Command.valueOf(command.toUpperCase());
    }
}
