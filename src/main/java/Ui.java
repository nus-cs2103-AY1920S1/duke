import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.sendLine();
        Ui.sendGreet();
        Ui.sendLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static void sendLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void sendGreet() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    public static void showLoadingError() {
        sendLine();
        System.out.println("File not found. Creating new list...");
        sendLine();
    }

    public static void sendMessage (String input) {
        System.out.println("     " + input);
    }

    public static void sendBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

}
