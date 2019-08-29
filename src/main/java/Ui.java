import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    String getNextLine() {
        return scanner.nextLine();
    }

    void showError(Exception e) {
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

    void testEmptyDescription(String taskDescription) throws EmptyTaskDescriptionException {
        if (taskDescription.matches("\\s*")) {
            throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    void testTimeFormat(String taskDescription) throws IncorrectTaskTimeFormatException {
        if (!(taskDescription.contains(" /by ") || taskDescription.contains(" /at "))) { // if '/by' and '/at' are absent
            throw new IncorrectTaskTimeFormatException("OOPS!!! No ' /by ' or ' /at ' detected! Please use the correct format!");
        }
    }
}
