import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick or cross symbol.
     *
     * @return A tick or cross to symbolize whether the task has been done.
     */
    public String getStatusIcon() {
        //System.out.println("\u2713");
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Method to mark the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints a statement informing the user that the bot
     * has added the task into the list.
     */
    public static void printGI() {
        //Duke temp = new Duke();
        Duke.printLine();
        Duke.printIndent();
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints the number of tasks in the list.
     *
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public static void printNumOfTasks() throws IOException {
        Duke.printIndent();
        System.out.println("Now you have " + Duke.getNumOfTasks() + " tasks in the list.");
        Duke.printLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    /**
     * Prints a statement to tell the user that the task has been removed.
     */
    public static void printRemove() {
        Duke.printLine();
        Duke.printIndent();
        System.out.println("Noted. I've removed this task.");
    }
}
