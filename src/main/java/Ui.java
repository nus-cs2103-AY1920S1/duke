import java.util.Scanner;
import java.util.ArrayList;

class Ui {
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    String userInput() {
        return this.sc.nextLine();
    }

    private void showLine() {
        System.out.println("___________________________________________");
    }

    void printFormat(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    void showWelcome() {
        printFormat("Hello, I'm Duke\nWhat can I do for you?");
    }

    void printTaskList(ArrayList<Task> tasks) {
        showLine();
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            counter++;
            System.out.println(counter + "." + task.toString());
        }
        showLine();
    }

    void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    void printDone(String task) {
        showLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        showLine();
    }

    void printRemoveMessage(String task, int size) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        showLine();
    }

    void printAddTaskMessage(String task, int size) {
        showLine();
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

}