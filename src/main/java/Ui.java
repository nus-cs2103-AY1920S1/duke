import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "________________________________________";
    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String showLine() {
        return "    " + DIVIDER;
    }

    public String showWelcome() {
        return DIVIDER + "\nWelcome to Duke!\n" + DIVIDER;
    }

    public String showLoadingError() {
        return "No previous data found!";
    }

    public String readCommand() {
        String command = sc.nextLine();

        return command;
    }

    public String printList(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            int index = i + 1;
            output.append("\n    " + index + "." + tasks.getTask(i));
        }

        return output.toString();
    }

    public String doneTaskMessage(Task task) {
        return "    Nice! I've marked this task as done:\n" + "    " + task;
    }

    public String addTaskMessage(TaskList taskList, Task task) {
        return "    Got it. I've added this task:\n" + "    " + task +
                "\n    Now you have " + taskList.getSize() + " tasks in the list.";
    }

    public String removeTaskMessage(TaskList taskList, Task task) {
        return "    Noted. I've removed this task:\n" + "    " + task +
                "\n    Now you have " + taskList.getSize() + " tasks in the list.";
    }

    public String findTaskMessage(TaskList taskList) {
        String output = printList(taskList);

        return "    Here are the matching tasks in your list:" + output;
    }

    public String showError(String message) {
        return message;
    }

    public String byeMessage() {
        return "    Bye. Hope to see you again soon!";
    }
}
