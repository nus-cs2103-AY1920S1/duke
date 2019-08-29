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

    public void exit() {
        //update response
        this.response = "Bye. Hope to see you again soon!";
        getResponse();
    }

    public void greet() {
        // Update response
        this.response = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        getResponse();
    }

    public void done(Task task) {
        // Update response
        this.response = "Nice! I've marked this task as done:\n       " + task;
        getResponse();
    }

    public void delete(Task task, TaskList tasks) {
        // Update response
        this.response = this.response = "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        getResponse();
    }
}
