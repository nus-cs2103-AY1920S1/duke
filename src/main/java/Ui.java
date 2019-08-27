import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static String oneLine = "____________________________________________________________\n";
    private static String frontSpace = "    ";


    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public void showLine() {
        addFrontSpace();
        System.out.println(onrLine)
    }

    public void addFrontSpace() {
        oneLine += frontSpace;
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showGreet() {
        showLine();
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = frontSpace + upperLine + greet + frontSpace + lowerLine;
        System.out.println(greet);
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println(frontSpace + " duke.txt file has problem!");
        showLine();
    }
}