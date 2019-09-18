package duke.ui;

public class Ui {
    StringBuilder outputMessage = new StringBuilder();

    private static final String MESSAGE_GREETING = "Hi! Welcome to PokeTask!\nWhat do you want to do today?";
    private static final String MESSAGE_BYE      = "Bye! See you again soon!";

    /**
     * Returns outputMessage to print to GUI.
     * @return outputMessage.
     */
    public String getMessage() {
        return outputMessage.toString();
    }

    /**
     * Makes outputMessage empty.
     */
    public void resetMessage() {
        outputMessage.setLength(0);
    }

    /**
     * Adds text to outputMessage for printing.
     * @param output Text to add to outputMessage.
     */
    public void append(String output) {
        outputMessage.append(output);
        outputMessage.append("\n");
    }

    /**
     * Returns the greeting message.
     * @return Greeting message.
     */
    public String getGreeting() {
        return MESSAGE_GREETING;
    }

    /**
     * Returns the farewell message.
     * @return Farewell message.
     */
    public String getBye() {
        return MESSAGE_BYE;
    }
}
