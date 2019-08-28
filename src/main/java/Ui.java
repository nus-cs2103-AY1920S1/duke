/**
 * Deals with interactions with the user.
 */
public class Ui {
    private String response;

    /**
     *
     * Prints out the response to be displayed to the user.
     */
    private void getResponse() {
        System.out.println("    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n");
    }


    /**
     * Prints out the response when user adds a new task.
     *
     * @param task task added by user.
     * @param tasks the list of tasks.
     */
    public void add(Task task, TaskList tasks) {
        //update response
        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        getResponse();
    }

    /**
     * Prints out the response showing a list of tasks.
     *
     * @param tasks the list of tasks.
     */
    public void list(TaskList tasks) {
        //get the list of tasks in the arraylist
        String listOfTasks = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks += (i + 1) + "." + tasks.getElement(i);
            if (i != tasks.getSize() - 1) listOfTasks += "\n     ";
        }
        this.response = listOfTasks; //update response
        getResponse();
    }

    /**
     * Prints out the response when user exits the application.
     *
     */
    public void exit() {
        //update response
        this.response = "Bye. Hope to see you again soon!";
        getResponse();
    }

    /**
     * Prints out the response when user enters the application
     *
     */
    public void greet() {
        //update response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        getResponse();
    }

    /**
     * Prints out the response when the user marks a task as done.
     *
     * @param task task done by user.
     */
    public void done(Task task) {
        this.response = "Nice! I've marked this task as done:\n       " + task; //update response
        getResponse();
    }

    /**
     * Prints out the response when the user deletes a task from the list.
     *
     * @param task task to be deleted.
     * @param tasks the list of tasks.
     */
    public void delete(Task task, TaskList tasks) {
        this.response = this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list."; //update response
        getResponse();
    }
}
