import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showAddTaskMessage(String taskDescription, int listSize) {
        String output = "Got it. I've added this task:\n"
                + taskDescription
                + "\nNow you have " + listSize + " tasks in the list.";
        System.out.println(output);
    }

    public void showDeleteTaskMessage(String taskDescription, int listSize) {
        String output = "Noted. I've removed this task: \n"
                + taskDescription
                + "\nNow you have " + listSize + " tasks in the list.";
        System.out.println(output);
    }

    public void showDoneMessage(String taskDescription) {
        System.out.println("Nice! I've marked this task as done: \n" + taskDescription);
    }

    public void showList(String[] taskDescriptionArray) {
        int taskLen = taskDescriptionArray.length;
        if (taskLen == 0) {
            System.out.println("You do not have any tasks in your list");
        } else {
            String output = "Here are the tasks in your list: ";
            for (int i = 0; i < taskLen; i ++) {
                String taskDescription = taskDescriptionArray[i];
                output += "\n" + (i + 1) + "." + taskDescription;
            }
            System.out.println(output);
        }
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";

        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println(welcomeMessage);
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        //Consider making DukeLoadingError
        System.out.println("No save file found... creating new save file");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}