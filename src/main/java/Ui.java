import java.util.Scanner;

class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Failed to Load past tasks :-(");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void addedTask(Task t, int size) {
        System.out.println("Got it. I've added this task: \n  " + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void markedAsDone(Task t) {
        System.out.println("Nice! I've marked this task as done: \n  " + t);
    }

    public void removedTask(Task t, int size) {
        System.out.println("Noted. I've removed this task: \n  " + t + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void printTasks(TaskList tasks) {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks.getList()) {
            System.out.println(idx + "." + t);
            idx++;
        }
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}




