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
        // Get the list of tasks in the Arraylist
        String listOfTasks = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < tasks.getSize(); i++) {
            listOfTasks += (i + 1) + "." + tasks.getElement(i);
            if (i != tasks.getSize() - 1) listOfTasks += "\n     ";
        }
        // Update response
        this.response = listOfTasks;
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
        // Update response
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
        // Update response
        this.response = "Nice! I've marked this task as done:\n       " + task;
        getResponse();
    }

    /**
     * Prints out the response when the user deletes a task from the list.
     *
     * @param task task to be deleted.
     * @param tasks the list of tasks.
     */
    public void delete(Task task, TaskList tasks) {
        // Update response
        this.response = this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        getResponse();
    }

    /**
     * Finds a task in the list of tasks which contains a keyword as part of its description.
     *
     * @param tasks a <code>TaskList</code> instance containing a list of tasks.
     * @param key   the keyword to be compared against each task description.
     * @throws DukeException Exceptions thrown when there are more than/ less than one keyword specified.
     */
    public void find(TaskList tasks, String key) throws DukeException {
        if (key.split(" ").length > 1 || key.equals(" ")) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! Please include at most/ at least one keyword." +
                    "\n    ____________________________________________________________\n");
        }
        // Get the list of tasks in the tasks list.
        String listOfTasks = "Here are the matching tasks in your list:\n     ";
        boolean isFound = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            String[] words = tasks.getElement(i).getDescription().split(" ");
            boolean containsKey = false;
            for (int j = 0; j < words.length; j++) {
                if (key.equalsIgnoreCase(words[j])) {
                    containsKey = true;
                    break;
                }
            }
            if (containsKey) {
                isFound = true;
                listOfTasks += (i + 1) + "." + tasks.getElement(i);
                listOfTasks += "\n     ";
            }
        }
        // Update response
        if (isFound) {
            int index = listOfTasks.lastIndexOf("\n     ");
            if (index > 0) {
                listOfTasks = listOfTasks.substring(0, index);
            }
            this.response = listOfTasks;
        } else {
            this.response = "\u2639 " + "OOPS!!! There is no matching tasks in your list.";
        }
        getResponse();
    }
}
