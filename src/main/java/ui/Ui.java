package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

/**
 * Determines what is displayed to user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Called at the start of program.
     */
    public  void showWelcome() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("    Hello! I'm \n" + logo + "\n    What can I do for you?");
        showLine();
    }

    /**
     * Called when execute method of ExitCommand is called.
     */
    public void showGoodbye(){
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Used for printing border.
     */
    public void showLine(){
        String output = "    ";
        for (int n = 0; n < 80; n++) {
            output += "_";
        }
        System.out.println(output);
    }

    /**
     * reads user input.
     * @return String to be parsed by parser.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Called when a task is entered by user.
     * Called when execute of AddTask is called.
     * @param new_task task entered.
     * @param task_Num number of tasks in Task List.
     */
    public void readTask(Task new_task, int task_Num){
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + new_task.toString());
        System.out.println("     Now you have " + task_Num + " tasks in the list.");
        showLine();
    }

    /**
     * Displays delete message.
     * Called when execute of DeleteCommand is called.
     * @param removed_Task
     * @param task_Num
     */
    public void readDelete(Task removed_Task, int task_Num){
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removed_Task.toString());
        System.out.println("     Now you have " + task_Num + " tasks in the list.");
        showLine();
    }

    /**
     * Displays message when a user finishes a task.
     * Calls when execute of EditCommand is called.
     * @param completed_Task Task which has been marked as done.
     */
    public void readDone(Task completed_Task){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + completed_Task.toString());
        showLine();
    }

    /**
     * Displays contents of Task List.
     * Called when execute of ListCommand is called.
     * @param tasks TaskList
     */
    public void readList(TaskList tasks){
        System.out.println("     Here are the task(s) in your list: ");
        System.out.println(tasks);
        showLine();
    }


    /**
     * Displays matching Tasks.
     * Called when execute method of FindCommand is called.
     * @param tasks TaskList of tasks with keyword.
     */
    public void showMatches(TaskList tasks){
        System.out.println("     Here are the matching task(s) in your list: ");
        System.out.println(tasks);
        showLine();
    }


    /**
     * Display message when DukeException is caught.
     * @param error_msg DukeException e.getMessage()
     */
    public void showError(String error_msg) {
        System.out.println("     ☹ OOPS!!! " + error_msg);
        showLine();
    }

    /**
     * Called when Duke Exception is thrown when instantiating Task List and/ or Storage Objects.
     * Called by run method in Duke class.
     */
    public void showLoadingError(){
        System.out.println("     ☹ OOPS!!! Loading Error");
        showLine();
    }

     /*   try {
            Task_List schedule = new Task_List(new Storage("data/duke.txt").load());
            Parser parser = new Parser(schedule);
            Scanner sc = new Scanner(System.in);
            System.out.println(new Border());
            System.out.println("    Hello! I'm \n" + logo + "\n    What can I do for you?");
            System.out.println(new Border() + "\n");
            String input = sc.nextLine();
            while (!input.equals("bye")) {
                parser.parse(input);
                input = sc.nextLine();
            }
            parser.parse(input);
            System.out.println((new Border()) + "\n     Bye. Hope to see you again soon! \n" + (new Border()));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }*/
}
