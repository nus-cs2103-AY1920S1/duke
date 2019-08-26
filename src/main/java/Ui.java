import java.util.Scanner;

public class Ui {
    protected String line = "    ____________________________________________________________";
    protected String welcomeMessage = "     Hello! I'm Duke\n     What can I do for you?";
    protected String byeMessage = "     Bye. Hope to see you again soon!";
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println(this.welcomeMessage);
        System.out.println(line);
    }

    public void showListMessage(TaskList tasks) {
        System.out.println(line);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.taskListSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(line);
    }

    public void showExceptionError(Exception e) {
        System.out.println(line);
        System.out.println("     " + e.getMessage());
        System.out.println(line);
    }

    public void showAddTaskMessage(Task task, int taskListSize) {
        System.out.println(line);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskListSize + (taskListSize > 1 ? " tasks in the list." : " task in the list."));
        System.out.println(line);
    }

    public void showMarkTaskAsDoneMessage(Task task) {
        System.out.println(line);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(line);
    }

    public void showDeleteTaskMessage(Task task, int taskListSize) {
        System.out.println(line);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskListSize + (taskListSize > 1 ? " tasks in the list." : " task in the list."));
        System.out.println(line);
    }

    public void showByeMessage() {
        System.out.println(line);
        System.out.println(this.byeMessage);
        System.out.println(line);
    }

    public void showFindTasksMessage(TaskList tasks) {
        System.out.println(line);
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.taskListSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(line);
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getByeMessage() {
        return byeMessage;
    }

    public void setByeMessage(String byeMessage) {
        this.byeMessage = byeMessage;
    }
}
