import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    public UI() {
    }

    public String readCommand() {
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printMessage(String random) {
        System.out.println(random);
    }

    public void showLoadingError() {
        System.out.println("Error, file not found");
    }
}
