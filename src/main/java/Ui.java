import java.util.ArrayList;

/**
 * The class used handle user interaction and dialogue
 */

class Ui {

    String goodByeMsg = "Bye. Hope to see you again soon!";
    private static String guidedUserInterfaceMsg = "";
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String welcomeMsg= logo + "\nHello! I'm Duke \nWhat can I do for you?";

    /**
     * A basic welcome message. Prints the duke logo and greeting.
     */


    void sayGreeting(){
        System.out.println(welcomeMsg);
    }


    /**
     * A basic goodbye message. Prints the goodbye.
     */

    void sayGoodbye(){
        System.out.println(goodByeMsg);
    }


    /**
     * A basic message to indicate a Task has been added to the Task array list.
     */

    void addTask(ArrayList<Task> taskList){
        printToConsoleAndGui("Got it. I've added this task:\n" + taskList.get(taskList.size() - 1).toString() +
                "\n" + "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * A basic message to indicate a Task has been removed from the Task array list.
     */

    void removeTask(ArrayList<Task> taskList, int taskNumber){

        assert (taskNumber <= taskList.size()) : "Somehow a task number larger than the task list size was entered";

        printToConsoleAndGui("Noted. I've removed this task:\n" + taskList.get(taskNumber).toString() +
                "\nNow you have " + (taskList.size()-1) + " tasks in the list.");
    }

    /**
     * A basic message to indicate a Task has been set as 'done' in the Task array list.
     */

    void setTaskDone(ArrayList<Task> taskList, int taskNumber){

        assert (taskNumber <= taskList.size()) : "Somehow a task number larger than the task list size was entered";

        printToConsoleAndGui("Nice! I've marked this task as done:\n" + taskList.get(taskNumber).toString());
    }

    /**
     * Returns the GUI message
     */

    String getGuidedUserInterfaceMsg() {
        return guidedUserInterfaceMsg;
    }

    /**
     * Sets the GUI message
     */

    void setGuidedUserInterfaceMsg(String guidedUserInterfaceMsg) {
        Ui.guidedUserInterfaceMsg = guidedUserInterfaceMsg;
    }

    /**
     * Prints the output to both the console and sets the GUI message
     */

    void printToConsoleAndGui (String message){
        System.out.println(message);
        setGuidedUserInterfaceMsg(message);
    }
}
