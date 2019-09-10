package trackr.ui;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints greeting message to user on application launch.
     */
    public String welcomeMsg() {
        String result = "";
        String line = "___________________________________________________\n";
        String greetMsg = "Hello! I'm Spongebob\nWhat can I do for you?\n";
        result = line + greetMsg + line;
        return result;
    }
}
