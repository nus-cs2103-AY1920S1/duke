import java.util.ArrayList;

public class Ui<Task> {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String line = "________________________________________";
    String indent = "    ";

    public Ui () {
    }

    public void showLoadingError() {
    }

    public void showLogo() {
        System.out.println("Hello from\n" + logo);
        System.out.println(indent + line);
        System.out.println(indent + "Hello! I'm Duke.\n" + indent + "What can I do for you today?");
        System.out.println(indent + line);
    }

    public void showByeResponse() {
        System.out.println(indent + "Bye! Hope to see you again soon.");
    }

    public void showListResponse() {
        System.out.println(indent + "Here are the tasks in your list:");
    }

    public void showNoTaskResponse() {
        System.out.println(indent + "You have no tasks.");
    }

    public void printLine() {
        System.out.println(indent + line);
    }

    public void showDoneResponse() {
        System.out.println(indent + "Nice! I've marked this task as done:");
    }

    public void showDeleteResponse(Task deletedTask) {
        System.out.println(indent + "Noted. I've removed the following task:");
        System.out.println(indent + indent + deletedTask);
    }

    public void showTotalNumberTasks(ArrayList<Task> list) {
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }

    public void printAddedTask(Task task, ArrayList<Task> list) {
        System.out.println(indent + "Got it. I've added this task:");
        System.out.println(indent + indent + task);
        System.out.println(indent + "Now you have " + list.size() + " task(s) in the list.");
    }

    public void printTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getList().size(); i++) {
            int k = i + 1;
            System.out.println(indent + k + ". " + tasks.getTask(i).toString());
        }
    }
}
