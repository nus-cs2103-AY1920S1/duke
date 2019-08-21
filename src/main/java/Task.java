import java.util.LinkedList;
import java.util.ListIterator;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static LinkedList<Task> taskList = new LinkedList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public static void addTask(Task task) {
        taskList.add(task);

        printLine();

        printIndent();
        System.out.println("Got it. I've added this task:");
        printIndent();
        System.out.println("  " + task.toString());
        printIndent();
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        printLine();
    }

    public void done() {
        this.isDone = true;
    }

    public static void doTask(int num) {
        taskList.get(num - 1).done();

        printLine();

        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        System.out.println("  " + taskList.get(num - 1).toString());

        printLine();
    }

    public static void printTaskList() {
        ListIterator<Task> iter = taskList.listIterator();
        int count = 1;

        printLine();

        printIndent();
        System.out.println("Here are the tasks in your list:");

        while (iter.hasNext()) {
            String currentTask = iter.next().toString();
            printIndent();
            System.out.println(count + "." + currentTask);
            count++;
        }

        printLine();
    }

    public static void printLine() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 60; i++) {
            line.append("_");
        }

        String stringLine = line.toString();
        printIndent();
        System.out.println(stringLine);
    }

    public static void printIndent() {
        System.out.print("    ");
    }

    @Override
    public String toString() {
        String task = "[" + this.getStatusIcon() + "] " + description;
        return task;
    }
}