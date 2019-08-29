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
}
