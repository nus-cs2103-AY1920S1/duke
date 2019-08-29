public class Ui {
    private String response;

    private void getResponse() {
        System.out.println("    ____________________________________________________________\n     "
                + this.response
                + "\n    ____________________________________________________________\n");
    }


    public void add(Task task, TaskList tasks) {
        //update response
        this.response = "Got it. I've added this task:\n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        getResponse();
    }

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

    public void exit() {
        //update response
        this.response = "Bye. Hope to see you again soon!";
        getResponse();
    }

    public void greet() {
        //update response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        getResponse();
    }

    public void done(Task task) {
        this.response = "Nice! I've marked this task as done:\n       " + task; //update response
        getResponse();
    }

    public void delete(Task task, TaskList tasks) {
        this.response = this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list."; //update response
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
