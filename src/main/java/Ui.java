import java.util.Scanner;

public class Ui {
    public String lastCommand = "";

    public Ui() {
    }

    public String getLastCommand() {
        return this.lastCommand;
    }
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        this.lastCommand = sc.nextLine();
        return lastCommand;
    }

    void showLoadingError() {
        System.out.println("    Error Reading File");
    }
    void showMessage(String msg) {
        System.out.println("    " + msg);
    }
    void showMessage(int numOfSpace, String msg) {
        for(int i = 0; i < numOfSpace; i++) {
            System.out.print(" ");
        }
        System.out.println(msg);
    }
    void showError(String err) {
        System.out.println("    " + err);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Type 'help' to view available commands");
        System.out.println("How may I help you?\n");
    }

    public void straightLine() {
        System.out.println("\n--------------------------------------");
    }
}
