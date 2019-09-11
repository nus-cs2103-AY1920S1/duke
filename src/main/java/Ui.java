import Tasks.Task;

public class Ui {
    public Ui() {}

    public void printHello() {
        System.out.println("Hello! I'm your task manager!");
        System.out.println("What can I do for you?");
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
    }

    public void printTaskRemoved(Task removed, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + removed);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printTodoError() {
        System.out.println("OOPS!!! The description of a todo cannot be empty.");
    }

    public void printDeadlineError() {
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
    }

    public void printEventError() {
        System.out.println("OOPS!!! The description of a event cannot be empty.");
    }

    public void printNoSuchTaskError() {
        System.out.println("OOPS!!! There is no such task.");
    }

    public void printUnknownCommandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}