package duke;
import java.util.Scanner;
import java.util.ArrayList;
import duke.task.Task;
/**
 * deals with interactions with user
 * */
public class Ui {
    public static String liner = "    ____________________________________________________________";

    //greet user
    public void showWelcome() {
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);
    }

    //say goodbye
    public void showBye() {
        System.out.println(liner);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(liner);
    }

    //show divider line
    public void showLine() {
        System.out.println(liner);
    }

    //show error message
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public void showLoadingError() {
        System.out.println("New task list generated: no existing file found!");
    }

    // print add task message
    public void printAddTask(Task newTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }

    // print done task message
    public void printDoneTask(Task currTask) {
        System.out.println(liner);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       "  + currTask.toString());
        System.out.println(liner);
    }

    // print deleted task message
    public void printDeletedTask(Task currTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       "  + currTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }

    // print all contents of task list
    public void showList(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            Task currTask = list.get(i);
            System.out.println("     " + num + ". " + currTask.toString());
        }
    }

    // print matching tasks from search result
    public void printMatches(ArrayList<Task> results) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            int num = i + 1;
            Task currTask = results.get(i);
            System.out.println("     " + num + ". " + currTask.toString());
        }
    }

    // get user input
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}