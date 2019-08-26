package duke.util;

import duke.task.Task;

public class Ui {
    /**
     * Pretty prints the output to the user with indentation of 4.
     * @param str the String to be printed
     */
    public void prettyPrint4(String str) {
        System.out.printf("    %s\n", str);    
    }

    /**
     * Pretty prints the output to the user with indentation of 4.
     * @param str the String to be printed 
     */
    public void prettyPrint6(String str) {
        System.out.printf("      %s\n", str);
    }

    /**
     * Sends task acknowledgement
     * @param task Task
     * @param size int the size of the task list
     */
    public void sendAddTaskAck(Task task, int size) {
        prettyPrint4("Got it. I've added this task:");
        prettyPrint6(task.toString());
        prettyPrint4(String.format("Now you have %d tasks in the list.", size));
    } 

    /**
     * Print welcome message.
     */
    public void printWelcome() {
        System.out.printf("Hello I'm Duke\nWhat can I do for you\n");
    }

    /**
     * Print write error.
     */
    public void printWriteError() {
        System.out.println("Sorry, failed to write to the disc");
    }

    /**
     * Print read error.
     */
    public void printReadError() {
        System.out.println("Sorry, failed to read from the disc");
    }
}
