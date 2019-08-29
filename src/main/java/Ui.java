import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "    " + "-".repeat(61);
    private Scanner scannerIn;

    public Ui() {
        this.scannerIn = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeRespond("Hello! I'm Duke", "What can I do for you?");
    }

    public void dukeRespond(String... inputs) {
        showLine();
        for (String str : inputs) {
            System.out.println("     " + str);
        }
        showLine();
    }

    private void showLine() {
        System.out.println(DIVIDER);
    }

    public void showLoadingError() {
        dukeRespond("Cannot read from file! Check if it exists?");
    }

    public void showError(String msg) {
        dukeRespond(msg);
    }

    public String readCommand() {
        //start listening for user input
        return this.scannerIn.nextLine();
    }

    protected void closeScanner() {
        this.scannerIn.close();
    }
}
