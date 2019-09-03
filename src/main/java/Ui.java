import java.util.ArrayList;

/**
 * The class used handle user interaction and dialogue
 */

public class Ui {

    String goodByeMsg = "Bye. Hope to see you again soon!";
    private static String guidedUserInterfaceMsg = "";

    /**
     * A basic welcome message. Prints the duke logo and greeting.
     */


    public void sayGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }


    /**
     * A basic goodbye message. Prints the goodbye.
     */

    public void sayGoodbye(){
        System.out.println(goodByeMsg);
    }


    /**
     * A basic message to indicate a Task has been added to the Task array list.
     */

    public void addTask(ArrayList<Task> taskList){
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
        guidedUserInterfaceMsg = "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() +
                "\n" + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * A basic message to indicate a Task has been removed from the Task array list.
     */

    public void removeTask(ArrayList<Task> taskList, int taskNumber){
        System.out.println("Noted. I've removed this task:\n" + taskList.get(taskNumber).toString());
        System.out.println("Now you have " + (taskList.size()-1) + " tasks in the list.\n");
        guidedUserInterfaceMsg = "Noted. I've removed this task:\n" + taskList.get(taskNumber).toString() +
                "\nNow you have " + (taskList.size()-1) + " tasks in the list.";
    }

    /**
     * A basic message to indicate a Task has been set as 'done' in the Task array list.
     */

    public void setTaskDone(ArrayList<Task> taskList, int taskNumber){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskNumber).toString() + "\n");
        guidedUserInterfaceMsg = "Nice! I've marked this task as done:\n" + taskList.get(taskNumber).toString();
    }

    public String getGuidedUserInterfaceMsg() {
        return guidedUserInterfaceMsg;
    }

    public void setGuidedUserInterfaceMsg(String guidedUserInterfaceMsg) {
        Ui.guidedUserInterfaceMsg = guidedUserInterfaceMsg;
    }
}
