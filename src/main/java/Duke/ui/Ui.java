package Duke.ui;

import Duke.task.Task;
import Duke.task.TaskList;

import java.util.ArrayList;

public class Ui {
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        showLine();
    }

    public void showBye() {
        showLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        showLine();
    }

    public void showError(String errorMsg) {
        showLine();
        System.out.printf("\t ☹ OOPS!!! %s\n", errorMsg);
        showLine();
    }

    public void showList(ArrayList<Task> list) {
        int count = 1;
        showLine();
        System.out.println("\t Here are the task(s) in your list:");
        for (Task task: list) {
            System.out.printf("\t %d. %s\n", count, task);
            count++;
        }
        showLine();
    }

    public void showAddedTask(Task addedTask, TaskList taskList) {
        showLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.printf("\t   %s\n", addedTask);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.getSize());
        showLine();
    }

    public void showDeletedTask(Task deletedTask, TaskList taskList) {
        showLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.printf("\t   %s\n", deletedTask);
        System.out.printf("\t Now you have %d task(s) in the list.\n", taskList.getSize());
        showLine();
    }

    public void showCompletedTask(Task completed) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.printf("\t   %s\n", completed.toString());
        System.out.println("\t____________________________________________________________");
    }

    public void showFound(TaskList taskList) {
        ArrayList<Task> list = taskList.getTaskList();
        int count = 1;
        showLine();
        System.out.println("\t Here are the matching tasks in your list:");
        for(Task task: list) {
            System.out.printf("\t %d. %s\n", count, task);
            count++;
        }
        showLine();
    }
}
