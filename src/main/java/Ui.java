import java.util.ArrayList;

public class Ui {
    public static String hr = "______________________________________________________________________";

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.printLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        this.printLine();
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        this.printLine();
    }
    public void showException (Exception e) {
        System.err.println(e.getMessage());
    }

    public void showNumberFormatError(String type) {
        switch(type) {
        case "done" : System.err.println(" :( OOPS!!! Invalid format." +
                    " Please enter the number of the task to be marked as done.");
            break;
        case "delete" : System.err.println(" :( OOPS!!! Invalid format." +
                    " Please enter the number of the task to be deleted.");
            break;
        }
    }

    public void printLine() {
        System.out.println(hr);
    }

    public void showTaskCreated(Task task, int noOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskDeleted(Task task, int noOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");
    }

    public void showTasks(TaskList taskList) {
        ArrayList<Task> arr = taskList.getArr();
        for(int i = 0; i < arr.size(); i++) {
            Task temp = arr.get(i);
            System.out.println((i + 1) + ". " + temp);
        }
    }

    public void showLoadingError() {
        System.err.println(" :( OOPS!!! Error occurred while loading the history file.");
    }
}
