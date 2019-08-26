import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    public void showWelcome() {
        this.showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        this.showLine();
    }

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("Loading error...");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
