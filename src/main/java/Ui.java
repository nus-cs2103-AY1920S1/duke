/**
 * Represents user interface that deals with interactions with user.
 */
public class Ui {

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

    public void showGoodbye() {
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Print out the TaskList in order.
     * @param TaskList The list of task.
     */
    public void showTaskList(TaskList TaskList) {
        System.out.println("\tHere are the tasks in your list:");

        int i = 1;
        for (Task t : TaskList.getList()) {
            System.out.println("\t" + i + ". " + t);
            i++;
        }
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________________");
    }

    /**
     * Takes in Command, parse it and returns the changed list.
     * @param str The String of command.
     * @param list The list of task.
     * @return The list of task.
     * @throws Exception If command is missing input.
     */
    public TaskList input(String str, TaskList list) throws Exception {
        String[] strArr = str.split(" ");
        Parser parser = new Parser();

        if (strArr[0].equals("list")) {
            showTaskList(list);
        } else if (strArr[0].equals("done")) {
            list = parser.parseDone(str, list);
        } else if (strArr[0].equals("todo")) {
            list = parser.parseTodo(str, list);
        } else if (strArr[0].equals("deadline")) {
            list = parser.parseDeadline(str, list);
        } else if (strArr[0].equals("event")) {
            list = parser.parseEvent(str, list);
        } else if (strArr[0].equals("delete")) {
            list = parser.parseDelete(str, list);
        } else {
            throw new DukeException("OOPS!!! I,m sorry, but I don't know what that means :-(");
        }

        return list;
    }

    public void showAddTask(Task t, TaskList list) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");

    }

    public void showTaskDone(Task t) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + t);
    }

    public void showDeleteTask(Task t, TaskList list) {
        System.out.println("\tNoted! I've removed this task:");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }
}
