import java.util.InputMismatchException;
import java.util.Scanner;

class Ui {
    private Scanner scanner;
    /**
     * Prints a string.
     */
    static void print(String str) {
        System.out.println(str);
    }

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showWelcome() {
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    void closeUi() {
        print("Bye. Hope to see you again soon!");
        scanner.close();
        showLine();
    }

    String readCommand() {
        return scanner.nextLine();
    }

    int readIndex() {
        try {
            return scanner.nextInt() - 1;
        } catch (InputMismatchException e) {
            print("OOPS!!! You need to enter a natural number.");
            return -1;
        }
    }

    void showLine() {
        print("_________________________");
    }
}
