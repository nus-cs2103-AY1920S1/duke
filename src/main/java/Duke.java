import java.util.Scanner;

public class Duke {
    static final String welcomeSentence = "Hello! I'm Duke\nWhat can I do for you?";
    static final String endingSentence = "Bye. Hope to see you again soon!";


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scanner = new Scanner(System.in);

        System.out.println(showFormattedStr(welcomeSentence));
        Command command = readCommand(scanner.next());
        while (!command.equals(Command.BYE)) {
            System.out.println(showFormattedStr(command.toString().toLowerCase()));
            command = readCommand(scanner.next());
        }
        System.out.println(showFormattedStr(endingSentence));
    }

    private static String showFormattedStr(String str) {
        final String indentation = "     ";
        final String separator = "    ____________________________________________________________\n";
        return separator + indentation + str.replace("\n", "\n" + indentation) + "\n" + separator;
    }

    private static Command readCommand(String command) {
        return Command.valueOf(command.toUpperCase());
    }
}
