public class UI {
    protected String line = "    __________________________________";
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public UI() {

    }

    public void showLine() {
        System.out.println(line);
    }

    public void showGreeting() {
        System.out.println(logo);
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line);
    }

    public void showLoadingError(String err) {
        System.out.println(err);
    }

    public void showGoodbye() {
        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void showTasksInList(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.getListSize(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.getItemAtIndex(i));
        }
    }

    public void showTaskIsDoneMsg(Task task) {
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("     " + task);
    }

    public void showTaskDeletedMsg(Task task, int taskListSize) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("     " + task);
        System.out.println("    Now you have " + taskListSize + " tasks in the list.");
    }

    public void showTaskAddedMsg(Task task, int taskListSize) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("    Now you have " + taskListSize + " in the list.");
    }

    public void showErrorMsg(String errMsg) {
        System.out.println("    " + errMsg);
    }

}
