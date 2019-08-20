import javax.swing.*;
import java.util.ArrayList;

public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    private ArrayList<Task> tasks;

    Duke() {
        tasks = new ArrayList<>();
    }

    void startDuke() {
        line();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        line();
    }

    void add(Task task) {
        //add task into the list of tasks
        tasks.add(task);
    }

    void listAll() {
        int counter = 1;

        //list out all the texts from the user
        line();
        System.out.println("\tHere are the tasks in your list:");
        for (Task t: tasks) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
        line();
    }

    void echo(Task task) {
        line();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        line();
    }

    void markAsDone(int i) {
        //set task as done
        Task specificTask = tasks.get(i - 1);
        specificTask.done();

        //display the task
        line();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + specificTask.toString());
        line();
    }

    void endDuke() {
        line();
        System.out.println("\tBye. Hope to see you again soon!");
        line();
    }

    private void line() {
        System.out.println("\t____________________________________________________________");
    }
}
