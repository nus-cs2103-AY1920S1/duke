import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void closeScanner() {
        sc.close();
    }

    public static void formattedPrint(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }

    public void showError(String errorMessage) {
        formattedPrint("☹ OOPS!!! " + errorMessage);
    }

    public void showLoadingError() {
        showError("loading error");
    }

    public void showWelcomeMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }

    public void showTasks(TaskList tasks) {
        StringBuilder builder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            builder.append("\n" + "     ");
            builder.append(i + 1).append(".").append(tasks.get(i).toString());
        }
        formattedPrint(builder.toString());
    }

    public void showAddTaskMessage(Task task, TaskList tasks) {
        String output = "Got it. I've added this task: " + "\n" + "       "
                + task.toString() + "\n" + "     "
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task in the list." : " tasks in the list.");
        formattedPrint(output);
    }

    public void showDeleteTaskMessage(TaskList tasks, int index) {
        String tempOut = "Noted. I've removed this task: " + "\n" + "       " +
                tasks.get(index).toString() + "\n" + "     " +
                "Now you have " + (tasks.size() - 1) +
                (tasks.size() - 1 == 1 ? " task in the list." : " tasks in the list.");
        formattedPrint(tempOut);
    }

    public void showDoneTaskMessage(TaskList tasks, int index) {
        String tempOut = "Nice! I've marked this task as done: " + "\n" + "       " +
                tasks.get(index).toString();
        formattedPrint(tempOut);
    }

    public void showExitMessage() {
        formattedPrint("Bye. Hope to see you again soon!");
    }

    public static void showCreateSaveFileMessage() {
        formattedPrint("data.json not found. Created a new one for you~");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
