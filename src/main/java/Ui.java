/**
 * Majorly deals with interactions with the user.
 */
public class Ui {
    private String response;

    /**
     * Prints out the response when called from other command methods.
     */
    private void getResponseDirect() {
        System.out.println("    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n");
    }

    /**
     * Prints out the response when user adds a new task to the list.
     *
     * @param task Stores the task added by user.
     * @param taskList Adds the task to the list of tasks.
     */
    public void add(Task task, TaskList taskList) {
        //Updates response
        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + taskList.getSize() + " tasks in the list.";
        getResponseDirect();
    }

    /**
     * Prints out the response showing a list of tasks in the file.
     *
     * @param taskList the list of tasks.
     */
    public void list(TaskList taskList) {
        //Gets the list of tasks in the arraylist
        String listOfTasks = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < taskList.getSize(); i++) {
            listOfTasks += (i + 1) + "." + taskList.getElement(i);
            if (i != taskList.getSize() - 1) listOfTasks += "\n     ";
        }
        this.response = listOfTasks; //update response
        getResponseDirect();
    }

    /**
     * Prints out the response when user types "bye"
     */
    public void exit() {
        //update response
        this.response = "Bye. Hope to see you again soon!";
        getResponseDirect();
    }

    /**
     * Returns default greeting with every call.
     */
    public void greet() {
        //update response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        getResponseDirect();
    }

    /**
     * Prints out the response when the user marks a task as done.
     *
     * @param task task done by user.
     */
    public void done(Task task) {
        this.response = "Nice! I've marked this task as done:\n       " + task; //update response
        getResponseDirect();
    }

    /**
     * Prints out the response when the user deletes a task from the list, called from different method.
     *
     * @param task task to be deleted.
     * @param taskList the list of tasks.
     */
    public void delete(Task task, TaskList taskList) {
        this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + taskList.getSize() + " tasks in the list."; //update response
        getResponseDirect();
    }
}
