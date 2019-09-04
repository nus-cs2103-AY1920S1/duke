package Ui;

import tasklist.Task;
import java.util.LinkedList;

/**
 * Contains all the text ui used in the program
 */

public class TextUi {
    private static final String DIVIDER ="    ____________________________________________________________";
    private static final String LOGO = " ____         _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";

    public TextUi(){}

    /**
     * ui for printing when a new a task has been added
     * @param task full task string including type, status, description and date(if applicable)
     * @param size total number of tasks in the list
     */
    public void printAddedTask(String task, int size){
        System.out.println(DIVIDER + "\n" +
                "     Got it. I've added this task:\n" +
                "       " + task + "\n" +
                "     Now you have " + size + " tasks in the list.\n" +
                DIVIDER);
    }

    /**
     * prints out the entire list
     * @param taskList is the linkedlist containing the tasks to be printed
     */
    public void printTaskList(LinkedList<Task> taskList){
        int i = 0;
        System.out.println(DIVIDER +"\n" +
                "     Here are the tasks in your list:");
        while (i < taskList.size()) {
            System.out.println("     "+ (i+1) + ". " + taskList.get(i).getOverallStatus());
            i++;
        }
        System.out.println(DIVIDER);
    }

    /**
     * ui for indicating that a task has been marked complete
     * @param completedtask full task name and description of the completed task
     */
    public void printCompletedTask(String completedtask){
        System.out.println(DIVIDER + "\n" +
                "     Nice! I've marked this task as done: \n" +
                "       "+ completedtask +"\n" + DIVIDER);
    }

    /**
     * ui for indicating that a task has been removed
     * @param task full task description
     * @param size total number of tasks on the list
     */
    public void printRemovedTask(String task, int size){
        System.out.println(DIVIDER + "\n" +
                "     Noted. I've removed this task: \n" +
                "       "+ task +"\n" +
                "     Now you have " + (size-1) + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    /**
     * prints the welcome message
     */
    public void printIntroduction(){
        System.out.println(LOGO + DIVIDER +"\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                DIVIDER);
    }

    /**
     * prints the program exit messsage
     */
    public void printGoodByeMsg(){
        System.out.println(DIVIDER + "\n" +
                "     Bye. Hope to see you again soon!\n" +
                DIVIDER);
    }

    /**
     * prints the error message for when the command word is wrongly formatted
     */
    public void printErrorMsg1(){
        System.out.println(DIVIDER + "\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know that command :-(\n" +
                DIVIDER);
    }

    /**
     * prints the error message for a general command failure
     */
    public void printErrorMsg2(){
        System.out.println(DIVIDER + "\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                DIVIDER);
    }

    /**
     * prints the error message for a loading error
     * @param tasknumber task that failed to load
     */
    public void printLoadingError(int tasknumber){
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but loading task " + tasknumber +
                " has failed, it will be removed\n" +
                "    ____________________________________________________________");
    }

    /**
     * prints the error message for a wrongly formatted date
     */
    public void printWrongDate(){
        System.out.println(DIVIDER + "\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't understand that date :-(\n" +
                DIVIDER);
    }

    /**
     * prints the ui for listing the found tasks
     * @param foundtasks list containing the tasks that were found
     */
    public void printFoundTasks(LinkedList<String> foundtasks){
        if (foundtasks.isEmpty()){
            System.out.println(DIVIDER + "\n"
                    + "     ☹ OOPS!!! I'm sorry, but I couldn't find anything :-(\n"
                    + DIVIDER);
        }else {
            System.out.println(DIVIDER + "\n"
                    + "     Here are the matching tasks in your list:");
            int i = 0;
            for (String s : foundtasks) {
                System.out.println("     " + ++i + "." + s);
            }
            System.out.println(DIVIDER);
        }
    }

}