package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import duke.task.Task;

/**
 * deals with interactions with user.
 * */
public class Ui {

    public static String liner = "    ____________________________________________________________";

    /**
     * greet user.
     * */
    public String showWelcome() {
        String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?";
        return liner + "\n" + greeting + "\n" + liner;
    }

    /**
     * print goodbye to user.
     * */
    public String showBye() {
        return liner + "\n\tBye. Hope to see you again soon!\n" + liner;
    }

    /**
     * print divider line.
     * */
    public String showLine() {
        return liner + "\n";
    }

    /**
     * show error message.
     * */
    public String showError(String message) {
        return liner + "\n" + message + "\n" + liner;
    }

    /**
     * print loading file error.
     * */
    public String showLoadingError() {
        return "New task list generated: no existing file found!";
    }

    /**
     * print add task message.
     * */
    public String printAddTask(Task newTask, int totalTasks) {
        return liner + "\n\tGot it. I've added this task:\n\t" + newTask.toString()
                + "\n\t" + "Now you have " + totalTasks + " tasks in the list.\n" + liner;
    }

    /**
     * print done task message.
     * */
    public String printDoneTask(Task currTask) {
        return liner + "\n\tNice! I've marked this task as done: \n\t"
                + currTask.toString() + "\n" + liner;
    }

    /**
     * print deleted task message.
     * */
    public String printDeletedTask(Task currTask, int totalTasks) {
        return liner + "\n\tNoted. I've removed this task:\n\t" + currTask.toString()
                + "\n\t" + "Now you have " + totalTasks + " tasks in the list.\n" + liner;
    }

    /**
     * print all contents in task list.
     * */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:\n");
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            Task currTask = list.get(i);
            sb.append("\t" + num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * print matching tasks from search result.
     * */
    public String printMatches(ArrayList<Task> results) {
        StringBuilder sb = new StringBuilder("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < results.size(); i++) {
            int num = i + 1;
            Task currTask = results.get(i);
            sb.append("\t" + num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * get user input.
     * */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}