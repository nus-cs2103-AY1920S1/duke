import java.util.ArrayList;

public class Ui {

    public void hi() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
    }

    public void done(Task t) {
        System.out.println("Nice! I've marked this task as done:\n  " + t);
    }

    public void addTask(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  " + t
                + "\nNow you have " + size + " tasks in the list.");
    }

    public void delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n  " + t +
                "\nNow you have " + size + " tasks in the list.");
    }

    public void find() {
        System.out.println("Here are the matching tasks in your list:");
    }

}
