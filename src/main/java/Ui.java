public class Ui {
    void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
