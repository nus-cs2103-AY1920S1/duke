import java.util.Scanner;

class Ui {

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void showLine() {
        System.out.println();
    }

    void showTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    void showLoadingError() {
        System.out.println("Sorry, I am not able to load the saved file.");
    }

    void showSaveError() {
        System.out.println("Sorry, I am not able to save your tasks.");
    }

    void showInvalidCommandError() {
        System.out.println("Sorry, I do not understand what you mean.");
    }

    void showInvalidDateError() {
        System.out.println("Sorry, I do not understand the date format.");
    }

    String readCommand() {
        return this.scanner.nextLine();
    }

}
