package duke.command;

import duke.tasks.Task;

import java.util.LinkedList;

public class Ui {

    /**
     * Prints out the line for formatting purposes
     */
    public void printline(){
        String line =  "\t____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Prints a new line character
     */
    //print new line for formatting
    public void printnewline(){
        System.out.println("\n");
    }

    /**
     * Shows the welcome message
     */
    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String name = "duke";
        printline();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printline();
    }

    /**
     * Confirmation message when the task is being added into the list
     *
     * @param t task that is being added into the list
     * @param i number of task that are in the list
     */
    public void takeInput(Task t, int i){
        printline();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + i + " tasks in the list");
        printline();
    }

    /**
     * Prints out the current list of tasks
     *
     * @param li List containing all the tasks
     */
    public void printList(LinkedList<Task> li){
        printline();
        System.out.println("\tHere are the Duke.tasks in your list:");
        for(int i = 0; i < li.size(); i++){
            int j = i+1;
            System.out.println("\t" + j + " " + li.get(i));
        }
        printline();
    }

    /**
     * Prints the done message to show that the task
     * has been completed
     *
     * @param task the task that has been completed
     */
    //completion of task confirmation
    public void printDone(Task task){
        printline();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task);
        printline();
    }

    /**
     * Prints the message to confirm that a task has
     * been deleted
     * @param task the task that is deleted
     * @param i the number of tasks left in the list
     */
    //completion of removal of task
    public void printDelete(Task task, int i){
        printline();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + i + " Duke.tasks in the list");
        printline();
    }


}