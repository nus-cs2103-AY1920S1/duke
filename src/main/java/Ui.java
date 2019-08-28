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

    void printFormat(String message) {
        System.out.println(message);
    }

    void showWelcome() {
        printFormat("Hello, I'm Duke\nWhat can I do for you?");
    }

    void printTaskList(ArrayList<Task> tasks) {
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            counter++;
            System.out.println(counter + "." + task.toString());
        }
    }

    void printBye() {
        printFormat("Bye. Hope to see you again soon!");
    }

    void printDone(String task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
    }

    void printRemoveMessage(String task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    void printAddTaskMessage(String task, int size) {
        System.out.println("Got it. I've added this task.");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    void showLine() {
        System.out.println("____________________________________________________________");
    }
}