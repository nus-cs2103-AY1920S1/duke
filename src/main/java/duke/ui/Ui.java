package duke.ui;

import duke.logic.TaskList;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showAddedTask(TaskList taskList) {

        System.out.println("Got it. I've added this task: ");
        System.out.println(String.format("    %s", taskList.getLast().toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getSize()));

    }

    public void showTaskList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> arr = taskList.getArr();
        int index  = 1;
        for (Task task : arr) {
            System.out.println(String.format("%d. %s", index , task.toString()));
            index++;
        }
    }

    public void showDeletedTask(TaskList taskList, Task t) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(String.format("    %s", t));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getArr().size()));
    }

    public void showWelcome()  {
        System.out.println("Hello I'm Duke! \nWhat can I do for you?");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showLoadingError() {
        System.out.println("Unable to load!");
    }

    public void showDoneTask(Task t) {
            System.out.println("Nice! I've marked this task as done");
            System.out.println(String.format("    %s", t));
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again!");
    }


}
