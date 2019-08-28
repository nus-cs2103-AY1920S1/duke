import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String command = scanner.nextLine().trim();
        return command;
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showNoTasksMsg();
        } else {
            System.out.println("Here are the tasks in your list:");
            ArrayList<Task> all_tasks = tasks.getTasks();
            for (int i = 1; i <= all_tasks.size(); i++) {
                Task task = all_tasks.get(i - 1);
                System.out.printf("%d.%s\n", i, task);
            }
        }
    }

    public void showTaskCompletionMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        showSingleTask(task);
    }

    public void showTaskAdditionMsg(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    public void showTaskDeletionMsg(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        showSingleTask(task);
        showTaskTotal(tasks);
    }

    public void showLoadingError() {
        System.out.println("Failed to load tasks. An empty list is created.");
    }

    public void showError(String message) {
        System.out.printf("\u2639 OOPS!!! %s\n", message);
    }

    public void showLine() {
        System.out.print(System.lineSeparator());
    }

    private void showNoTasksMsg() {
        System.out.println("There are currently no tasks in your list.");
    }

    private void showSingleTask(Task task) {
        System.out.printf("  %s\n", task);
    }

    private void showTaskTotal(TaskList tasks) {
        int total = tasks.size();
        System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
