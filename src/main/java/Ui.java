import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    public static final String DIVIDER = "    ____________________________________________________________";
    public static final String BLANKSPACE = "     ";
    private Scanner sc;
    private String currLine;

    public Ui() {
        sc = new Scanner(System.in);
    }

    String readCommand() throws DukeException {
        String fullCommand = sc.nextLine().trim();
        if (fullCommand.equals("")) {
            throw new DukeException("Please enter a command. Type 'help' for a list of all valid commands.");
        }
        currLine = fullCommand;
        return fullCommand;
    }

    void showLine() {
        System.out.println(DIVIDER);
    }


    public void showWelcome() {
        showLine();
        dukeEcho("Hi! My name is Duke, what can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        dukeEcho("Bye! Hope to see you again soon!");
    }

    public void showError(String message) {
        dukeEcho(message);
    }

    void dukeEcho(String... messages){
        for (String msg : messages) {
            System.out.println(BLANKSPACE + msg);
        }
    }


}
