import java.util.Scanner;

public class Ui {

    /**
     * Prints welcome message when Duke.java is run
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" +
                "What can I do for you?\n\n" +
                "To add task, type: \n" +
                "    todo + 'name of task'\n" +
                "    deadline + 'name of task' + 'DD/MM/YY hhmm'\n" +
                "    event + 'name of event' + 'DD/MM/YY hhmm'\n\n" +
                "To delete task, type:\n" +
                "    delete + 'task number'\n\n" +
                "To mark task as done, type:\n" +
                "    done + 'task number\n\n" +
                "To display all tasks, type:\n" +
                "    list\n\n" +
                "To search for tasks, type:\n" +
                "    find + 'keyword'";
    }

    /**
     * Prints loading error message
     */
    public void showLoadingError() {
    	System.out.println("    ____________________________________________________________");
        System.out.println("     No task to load");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints the divider line
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints out error message after exception is caught
     *
     * @param errorMessage Message of exception that is caught
     */
    public void showError(String errorMessage) {
        System.out.println("     " + errorMessage);
    }

    /**
     * Returns user input as a String
     *
     * @return User input as String
     */
    public String readCommand() {
    	Scanner sc = new Scanner(System.in);
    	return sc.nextLine();
    }
}
