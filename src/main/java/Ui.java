public class Ui {
    private String response;

    private void getResponseDirect() {
        System.out.println("    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n");
    }

    public void add(Task task, TaskList taskList) {
        //Updates response
        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + taskList.getSize() + " tasks in the list.";
        getResponseDirect();
    }

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

    public void exit() {
        //Updates response
        this.response = "Bye. Hope to see you again soon!";
        getResponseDirect();
    }

    public void greet() {
        //Updates response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        getResponseDirect();
    }

    public void done(Task task) {
        this.response = "Nice! I've marked this task as done:\n       " + task; //Updates response
        getResponseDirect();
    }

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

