import java.util.Scanner;

public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String opening = logo + "\nHello! I'm Duke\nWhat can I do for you?";
    private String closing = "Bye. Hope to see you again soon!";
    private Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    public String[] getCommand() {
        String[] fullCommand = {sc.next(), sc.nextLine().trim()};
        return fullCommand;
    }

    public void sayWelcome() {
        System.out.println(opening);
    }

    public void sayBye() {
        System.out.println(closing);
    }

    public void displayError(String error) {
        System.out.println(error);
    }

    public void output(String s) {
        System.out.println(s);
    }
}
