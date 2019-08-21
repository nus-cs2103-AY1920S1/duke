import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printLogo();
        printWelcome();

        while (true) {
            if (sc.hasNextLine()) {
                String command = sc.nextLine();
                switch (command) {
                    case "bye":
                        printBye();
                        return;
                    default:
                        printMessage(command);
                        break;
                }
            }
        }
    }

    private static void printIndent() {
        System.out.print("\t");
    }

    private static void printIndentWSpace() {
        System.out.print("\t ");
    }

    private static void printTopLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printBotLine() {
        String line = "____________________________________________________________";
        printIndent();
        System.out.println(line);
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printWelcome() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Hello! I'm Duke");
        printIndentWSpace();
        System.out.println("What can I do for you?");
        printBotLine();
    }

    private static void printBye() {
        printTopLine();
        printIndentWSpace();
        System.out.println("Bye. Hope to see you again soon!");
        printBotLine();
    }

    private static void printMessage(String msg) {
        printTopLine();
        printIndentWSpace();
        System.out.println(msg);
        printBotLine();
    }
}
