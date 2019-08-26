public class Ui {

    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    public void showWelcome() {
        String initialMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(initialMessage);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        return;
    }
}
