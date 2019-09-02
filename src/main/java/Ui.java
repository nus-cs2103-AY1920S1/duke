import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void greeting () {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list () { System.out.println("Here are the tasks in your list:"); }

    public void add (ArrayList<Task> list) {

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1));

        System.out.printf("Now you have %d tasks in the list.\n", list.size());

    }


    public void delete(ArrayList<Task> list, Task task) {

        System.out.println(task);

        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    public void done(Task task) {

        System.out.println("Nice! I've marked this task as done:");

        System.out.println(task);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {

        return scanner.nextLine();
    }

    public void abort() {
        System.out.println("SORRY SOMETHING WENT SERIOUSLY WRONG! \nGoodbye!");
    }
}
