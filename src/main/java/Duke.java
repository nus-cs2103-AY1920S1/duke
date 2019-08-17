import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FRONTSPACES = "   "; // 3 spaces
    private static final String LINE = "   __________________________________________________\n"; // line width = 50 space, with 3 spaces in front

    public static void main(String[] args) {
        new Duke().start();
    }

    private void start() {
        printWelcomeMessage();
        receiveCommand();
    }

    public void receiveCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if ("bye".equals(command)) {
                echo("Bye. Hope to see you again!");
                break;
            } else {
                echo(command);
            }
        }
    }

    public void echo(String... strings) {
        echo(null, strings);
    }

    public void echo(List<String> stringsList) {
        echo(stringsList, null); // Cannot remove the null behind or else lead to infinite loop!
    }

    private void echo(List<String> stringsList, String... strings) {
        printLine();

        if (stringsList != null) {
            stringsList.forEach(string -> {
                System.out.print(FRONTSPACES + string + "\n");
            });
        } else {
            for (String string : strings) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        printLine();
    }

    private void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "   |  _ \\ _   _| | _____ \n"
                + "   | | | | | | | |/ / _ \\\n"
                + "   | |_| | |_| |   <  __/\n"
                + "   |____/ \\__,_|_|\\_\\___|\n";

        echo(logo, "Hello! I'm Duke", "What can I do for you?");
    }

    // Print a line made up of underscore _ characters
    private void printLine() {
        System.out.print(LINE);
    }
}
