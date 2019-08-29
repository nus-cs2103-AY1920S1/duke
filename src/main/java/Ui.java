public class Ui {

    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "     ";

    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.printf("Hello from\n" + logo);
    }

    public void showLine() {
        System.out.printf(LINE);
    }

    public void showWelcome() {
        System.out.printf(LINE + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n"
                + LINE);
    }

    public void showGoodbye() {
        System.out.printf(LINE + INDENT + "Bye. Hope to see you again soon!\n" + LINE);
    }

    public void showDone(String doneTask) {
        System.out.printf(LINE + INDENT + "Nice! I've marked this task as done: \n" + INDENT +
                doneTask + "\n" + LINE);
    }

    public void showAdd(String task) {
        System.out.printf(LINE + INDENT + "Got it. I've added this task: \n" + INDENT + " " + task + "\n");
    }

    public void showListHeader() {
        System.out.printf(LINE + INDENT + "Here are the tasks in your list:\n");
    }

    public void showRemove(String removed) {
        System.out.printf(LINE + INDENT + "Noted. I've removed this task:\n" + INDENT + " " + removed + "\n");
    }

    public void showUnknownError() {
        System.out.printf(LINE + INDENT + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);
    }

    public void printDukeException(DukeException e) {
        System.out.printf(LINE + INDENT + e.toString() + "\n"  + LINE);
    }

    public void printFileNotFoundException() {
        System.out.printf(LINE + INDENT + "oops, task list is not found :o\n" + LINE);
    }

    public void printIOException() {
        System.out.printf(LINE + INDENT + "oops, something went wrong :(\n" + LINE);
    }

    public void showLoadingError() {
        System.out.printf(LINE + INDENT + "oops, something went wrong\n" + LINE);
    }

    public void showCount(int numOfTask) {
        if (numOfTask < 2) {
            System.out.printf(INDENT + "Now you have %d task in the list.\n", numOfTask);
        } else {
            System.out.printf(INDENT + "Now you have %d tasks in the list.\n", numOfTask);
        }
    }

    public void showTask(int index, String task) {
        System.out.printf(INDENT + " " + index + ". " + task + "\n");
    }

    public void printTaskIndexMismatchException() {
        System.out.printf(LINE + INDENT +
                "☹ OOPS!!! I cannot recognise that task index. :-(" + "\n" + LINE);
    }
}
