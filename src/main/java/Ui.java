import java.util.Scanner;

public class Ui {

    private String lastCommand;

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        lastCommand = scanner.nextLine();
        return lastCommand;
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void printLine(String string) {
        System.out.println(string);
    }

    public void greetings() {
        System.out.println("Hello! I am Jeong's Slave");
        System.out.println("What can I do for you?");
    }
}
