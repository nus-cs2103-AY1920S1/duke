public class Ui {

    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
