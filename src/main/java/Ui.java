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
        //Updates response
        this.response = "Bye. Hope to see you again soon!";
        getResponseDirect();
    }

    /**
     * Returns default greeting with every call.
     */
    public void greet() {
        //Updates response
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
        this.response = "Nice! I've marked this task as done:\n       " + task; //Updates response
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
                + "\n     Now you have " + taskList.getSize() + " tasks in the list."; //Updates response

        getResponseDirect();
    }

    /**
     * Finds a task in the list of tasks which contains a keyword.
     *
     * @param taskList a <code>TaskList</code> instance containing a list of tasks.
     * @param keyword keyword input for search.
     * @throws DukeException Exceptions thrown if 0 keyword or more than 1 keywords are specified.
     */
    public void find(TaskList taskList, String keyword) throws DukeException {
        if (keyword.split(" ").length > 1 || keyword.equals(" ")) {
            throw new DukeException("    ____________________________________________________________\n     " +
                    "\u2639" + " OOPS!!! Please include at most/ at least one keyword." +
                    "\n    ____________________________________________________________\n");
        }

        // Get the list of tasks in the tasks list.
        String listOfMatchingTasks = "Here are the matching tasks in your list:\n     ";
        boolean isFound = false;
        for (int i = 0; i < taskList.getSize(); i++) {
            String words[] = taskList.getElement(i).getDescription().split(" ");
            boolean containsKeyword = false;
            for (int j = 0; j < words.length; j++) {
                if (keyword.equalsIgnoreCase(words[j])) {
                    containsKeyword = true;
                    break;
                }
            }
            if (containsKeyword) {
                isFound = true;
                listOfMatchingTasks += (i + 1) + "." + taskList.getElement(i);
                listOfMatchingTasks += "\n     ";
            }
        }

        // Updates response
        if (isFound) {
            int index = listOfMatchingTasks.lastIndexOf("\n     ");
            if (index > 0) {
                listOfMatchingTasks = listOfMatchingTasks.substring(0, index);
            }
            this.response = listOfMatchingTasks;
        } else {
            this.response = "\u2639 " + "OOPS!!! There is no matching tasks in your list.";
        }

        getResponseDirect();
    }
}

