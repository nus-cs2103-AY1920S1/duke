public class Task {
    protected String description;
    protected boolean isDone;
    protected static String[] checklist = new String[100];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Returns tick or cross symbol.
    public String getStatusIcon() {
        //System.out.println("\u2713");
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;

    }

    public static void printGI() {
        //Duke temp = new Duke();
        Duke.printLine();
        Duke.printIndent();
        System.out.println("Got it. I've added this task:");
    }

    public static void printNumOfTasks() {
        Duke.printIndent();
        System.out.println("Now you have " + Duke.counter + " tasks in the list.");
        Duke.printLine();
    }

    public String toString() {
        return "[" + getStatusIcon() + "]";
    }

    public static void printRemove() {
        Duke.printLine();
        Duke.printIndent();
        Duke.counter--;
        System.out.println("Noted. I've removed this task.");
    }
}
