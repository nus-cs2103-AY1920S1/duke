import java.util.Scanner;

public class Ui {
    private Scanner in;
    private String loadingErrorMessage = "Oh no! Could not load tasks from file :(";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String welcomeMessage = "Hello from \n" + logo + "What can I do for you?";
    private String line = "____________________________________________________________";
    private String goodbyeMessage = "Bye. Hope to see you again soon!";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println(loadingErrorMessage);
    }

    public void showWelcome() {
        System.out.println(welcomeMessage);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    // probably would want to abstract these messages out into Ui class
//    public void showTasks() {
//        System.out.println()
//    }

    public void showGoodbye() {
        System.out.println(goodbyeMessage);
    }
}
