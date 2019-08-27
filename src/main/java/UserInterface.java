import java.util.Scanner;

class UserInterface {

    UserInterface() {}

    void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        showLine();
    }

    void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    void showLoadingError() {
        System.out.println("No data was loaded into the task list.");
    }

    void showError(String message) {
        System.out.println(message);
    }
}
