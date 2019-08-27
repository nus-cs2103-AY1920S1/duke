import tasks.TaskList;

/**
 * Represents a UI object that communicates with the user.
 */
public class Ui {
    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows the message when a task has been added.
     */
    public void showAdded() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Shows the message when a wrong input has been detected.
     */
    public void inputWrong() {
        System.out.println("Input format is wrong.");
    }

    /**
     * Shows the message when a task has been deleted.
     */
    public void showDeleted() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Shows the goodbye message.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    /**
     * Prints out the list of tasks.
     * @param t taskList.
     * @param s Storage where the data has been stored.
     */
    public void printList(TaskList t, Storage s) {
        System.out.println("Here are the tasks in your list:");
        int size = t.getCommandList().size();
        for (int i = 1; i < size + 1; i++) {
            System.out.print(i + ".");
            System.out.println(t.getCommandList().get(i - 1));
        }
    }

    /**
     * Prints out the error message.
     * @param e Error.
     */
    public void showError(String e) {
        System.out.println(e);
    }
}
