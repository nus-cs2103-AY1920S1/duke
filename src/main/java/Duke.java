import java.util.Scanner;

/**
 * Represents Duke, a personal chatbot assistant.
 */
public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        String horizontalLine = "________________________________________________\n";
        String openingMessage = horizontalLine
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + horizontalLine;
        System.out.println(openingMessage);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("bye")) { //user wants to exit
                System.out.print(horizontalLine);
                exit();
                System.out.print(horizontalLine);
                return;
            } else {
                System.out.println(horizontalLine + command + "\n" + horizontalLine);
            }
        }
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
