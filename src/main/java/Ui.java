import java.util.Scanner;

public class Ui {

    Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    //Display welcome message
    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    //Read new command inputs
    String readCommand() {
        return scanner.nextLine();
    }

    //Shows the divider line
    void showLine() {
        System.out.println("____________________________________________________________");
    }

    //Display error messages
    void showError(String message) {
        System.out.println(message);
    }

    //Display exit message
    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
