import Task.Task;

import java.util.ArrayList;

public class Ui {

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n What can I do for you? \n" );
    }

    public void taskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: \n " +
                task.toString() + "\n");
    }

    public void taskDeleted(Task task) {
        System.out.println("Noted. I've removed this task:  \n " +
                task.toString());
    }

    public void showNumberOfTasks(ArrayList<Task> list) {
        System.out.println("Now you have " + list.size() + " task(s) in the list.");
    }

    public void taskCreated(Task task) {
        System.out.println("Got it. I've added this task: \n"
                + task.toString());
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
//                    System.out.println(currTask.getClass());
            System.out.print((i+1) + ". " + currTask.toString() + "\n");
        }
    }

    public void showDescriptionEmptyError() {
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }

    public void showWrongCommandError() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! Error loading file.");
    }

    public void noSuchTaskError() {
        System.out.println("Unable to find task. Please try again." );
    }
}
