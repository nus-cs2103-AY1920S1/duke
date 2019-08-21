import java.util.Scanner;

public class Duke {

    public static void printWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void printEcho(String command) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + command);
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    public static void main(String[] args) {
        printWelcome();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            if (currLine.equals("bye")) {
                printBye();
                sc.close();
                System.exit(0);
            } else {
                printEcho(currLine);
            }
        }
    }
}
