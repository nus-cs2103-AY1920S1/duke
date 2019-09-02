import java.util.Scanner;

class Ui {

    private Scanner sc = new Scanner(System.in);
    private String line = "    ____________________________________________________________";

    void showWelcome() {
        System.out.println(line + "\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" + line);
    }

    void showLoadingError(String e) {
        System.err.println(e);
    }

    void showError(String e) {
        System.err.println(e);
    }

    String readCommand() {
        return sc.nextLine();
    }

    void showLine() {
        System.out.println(line);
    }

    void showDeleted(Task task, int numOfTasks) {
        System.out.println(
                "     Noted. I've removed this task: \n       " + task + "\n" +
                        "     Now you have " + numOfTasks + " tasks in the list.");
    }

    void showBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    void showList(TaskList taskList) {
        System.out.print(taskList);
    }

    void showAdded(Task task, int numOfTasks) {
        System.out.println("     Got it. I've added this task: \n       " + task +
                "\n     Now you have " + numOfTasks + " tasks in the list.");
    }

    void showDone(Task task) {
        System.out.println("     Nice! I've marked this task as done: \n       " + task);
    }
}