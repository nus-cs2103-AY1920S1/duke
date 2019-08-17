import java.util.Scanner;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String TABS = "     ";

    /**
     * Prints out system messages after formatting
     * @param command
     */
    public static void formatDisplayedMessages(String command) {
        System.out.println(LINE);
        String[] parsedCommands = command.split("\n");
        for (String line : parsedCommands) {
            System.out.println(TABS + line);
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        formatDisplayedMessages(greeting);
        Scanner sc = new Scanner(System.in); //gets commands from user
        String userCommand = sc.nextLine();

        //if command is not bye, echo userCommand
        while (!userCommand.equals("bye")) {
            formatDisplayedMessages(userCommand);
            userCommand = sc.nextLine();
        }

        //program runs this command when userCommand is bye
        formatDisplayedMessages("Bye. Hope to see you again soon!");
    }
}
