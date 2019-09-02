package jermi.component;

/**
 * A class that deals with interaction with the user.
 */
public class Ui {
    /**
     * Formats a message.
     *
     * @param message Message.
     * @return Formatted message.
     */
    private String formatMessage(String message) {
        return message + "\n";
    }

    /**
     * Returns formatted messages.
     *
     * @param messages Message.
     * @return Formatted messages.
     */
    public String echo(String... messages) {
        String border = "_______________________________________________\n";
        StringBuilder toEcho = new StringBuilder(border);
        for (String message : messages) {
            toEcho.append(this.formatMessage(message));
        }
        System.out.println(toEcho);
        return toEcho.toString();
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String greet() {
        return this.echo("Hello! I'm Jermi", "What can I do for you?");
    }

    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String exit() {
        return this.echo("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the entire user input and returns the input command.
     *
     * @param input User input.
     * @return Input command.
     */
    public String readCommand(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Reads the entire user input and returns the input details.
     *
     * @param input User input.
     * @return Input details.
     */
    public String readDetails(String input) {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}