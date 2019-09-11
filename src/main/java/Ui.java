import tasks.Task;

/**
 * Deals with the interactions with the user.
 */
public class Ui {
    public Ui() {}

    /**
     * Prints hello when the application runs.
     */
    public void printHello() {
        System.out.println("Hello! I'm your task manager!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints bye when the application ends.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the details of the task added.
     */
    public void printTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the details of the task marked done.
     */
    public void printTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
    }

    /**
     * Prints the details of the task removed.
     */
    public void printTaskRemoved(Task removed, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + removed);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints todo error.
     */
    public void printTodoError() {
        System.out.println("OOPS!!! The todo description cannot be empty.");
    }

    /**
     * Prints deadline error.
     */
    public void printDeadlineError() {
        System.out.println("OOPS!!! The deadline description cannot be empty.");
    }

    /**
     * Prints event error.
     */
    public void printEventError() {
        System.out.println("OOPS!!! The event description cannot be empty.");
    }

    /**
     * Prints task error.
     */
    public void printNoSuchTaskError() {
        System.out.println("OOPS!!! There is no such task.");
    }

    /**
     * Prints command error.
     */
    public void printUnknownCommandError() {
        System.out.println("OOPS!!! I'm sorry, I don't know what you mean :(");
    }
}