import javafx.application.Platform;

/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Prints message to indicate error loading the target file.
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints welcome message when application starts up.
     */
    public void showWelcome() {
        System.out.println("Hello there, this is Donna from Suits.\nFeel free to enter 'help' to see all the commands" +
                " I can help you with.");
    }

    public String showWelcomeForGui() {
        return "Hello there, this is Donna from Suits.\nFeel free to enter 'help' to see all the commands" +
                " I can help you with.";
    }

    /**
     * Prints exit message and exits the application.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public String exitForGui() {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints error message.
     * @param errorMessage input error message from any exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showHelp() {
        System.out.println("Sure, let me help you out. Here are all the commands you can enter:");
        System.out.println("bye - terminates the application");
        System.out.println("list - displays all tasks in the task list");
        System.out.println("delete <n> - deletes the nth task in the task list. Note that n is a positive integer.");
        System.out.println("done <n> - marks the nth task in the task list as done. Note that n is a positive integer.");
        System.out.println("todo <description> - adds a to-do task into the task list");
        System.out.println("event <description> /at <dd/MM/yyyy HHmm> - adds an event into the task list");
        System.out.println("deadline <description> /by <dd/MM/yyyy HHmm> - adds a deadline into the task list");
        System.out.println("find <keyword> - finds tasks(s) with description matching the keyword");
    }

    public String showHelpForGui() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sure, let me help you out. Here are all the commands you can enter:\n\n");
        sb.append("bye - terminates the application\n");
        sb.append("list - displays all tasks in the task list\n");
        sb.append("delete <n> - deletes the nth task in the task list. Note that n is a positive integer.\n");
        sb.append("done <n> - marks the nth task in the task list as done. Note that n is a positive integer.\n");
        sb.append("todo <description> - adds a to-do task into the task list\n");
        sb.append("event <description> /at <dd/MM/yyyy HHmm> - adds an event into the task list\n");
        sb.append("deadline <description> /by <dd/MM/yyyy HHmm> - adds a deadline into the task list\n");
        sb.append("find <keyword> - finds tasks(s) with description matching the keyword");
        return sb.toString();
    }
}
