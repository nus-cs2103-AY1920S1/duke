import java.util.Scanner;

public class Ui {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final Scanner scan = new Scanner(System.in);
    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        String command = scan.nextLine();
        return command;
    }

    public void showLoadingError() {
        System.out.println("Unable to load files");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
