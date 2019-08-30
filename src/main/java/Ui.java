/**
 * Represents user interface that deals with interactions with user.
 */
public class Ui {

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        showLine();
    }

    /**
     * Prints out goodbye message.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Print out the TaskList in order.
     * @param tasks The list of task.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("\tHere are the tasks in your list:");

        int i = 1;
        for (Task t : tasks.getList()) {
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }

    /**
     * Prints out line.
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________________");
    }

    /**
     * Takes in Command, parse it and returns the changed list.
     * @param str The String of command.
     * @param tasks The list of task.
     * @return The list of task.
     * @throws Exception If command is missing input.
     */
    public TaskList input(String str, TaskList tasks) throws Exception {
        String[] strArr = str.split(" ");
        Parser parser = new Parser();

        if (strArr[0].equals("list")) {
            showTaskList(tasks);
        } else if (strArr[0].equals("done")) {
            tasks = parser.parseDone(str, tasks);
        } else if (strArr[0].equals("todo")) {
            tasks = parser.parseTodo(str, tasks);
        } else if (strArr[0].equals("deadline")) {
            tasks = parser.parseDeadline(str, tasks);
        } else if (strArr[0].equals("event")) {
            tasks = parser.parseEvent(str, tasks);
        } else if (strArr[0].equals("delete")) {
            tasks = parser.parseDelete(str, tasks);
        } else if (strArr[0].equals("find")) {
            showMatchingTask(parser.parseFind(str, tasks));
        } else {
            throw new DukeException("OOPS!!! I,m sorry, but I don't know what that means :-(");
        }

        return tasks;
    }

    /**
     * Prints out task added.
     * @param t The Task that is added into TaskList.
     * @param tasks The TaskList of user.
     */
    public void showAddTask(Task t, TaskList tasks) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Prints out task mark as done.
     * @param t The Task that is marked as done.
     */
    public void showTaskDone(Task t) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + t);
    }

    /**
     * Prints out task that is deleted.
     * @param t The Task deleted.
     * @param tasks The TaskList of user.
     */
    public void showDeleteTask(Task t, TaskList tasks) {
        System.out.println("\tNoted! I've removed this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints out all Task that match the keyword.
     * @param tasks The TaskList of matched Task.
     */
    public void showMatchingTask(TaskList tasks) {
        System.out.println("\tHere are the matching tasks in your list:");

        int i = 1;
        for (Task t : tasks.getList()) {
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }
}
