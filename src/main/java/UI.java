import java.util.Scanner;

public class UI {

    public static final String divider = "\t____________________________________________________________";
    public static final String messageFrontInden = "\t  ";

    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String inputStr = sc.nextLine();

        return inputStr;
    }

    public void showWelcome() {
        this.showLine();
        String logo = "\t   ____        _        \n"
                + "\t  |  _ \\ _   _| | _____ \n"
                + "\t  | | | | | | | |/ / _ \\\n"
                + "\t  | |_| | |_| |   <  __/\n"
                + "\t  |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        printWithInden("Hello! I'm Duke");
        printWithInden("What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(divider);
    }

    public void showLoadingError() {
        System.out.println("Unable to load previous data! A new task list is created.");
    }

    public void showLoadingSuccessful() {
        System.out.println("Previous task list loaded successfully.");
    }

    public void showError(String message) {
        printWithInden(message);
    }

    public void printWithInden(String message) {
        System.out.println(messageFrontInden + message);
    }
}
