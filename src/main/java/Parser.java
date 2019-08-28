/**
 * Encapsulates the Parser object that is responsible of interpreting user commands.
 */

public class Parser {
    String userCommand;
    String due;
    String taskDescription;

    /**
     * Constructs the Parser object and performs the interpretation of user commands.
     * @param input This is the input String of the user command.
     */
    Parser(String input) {
        String[] dueSplit = input.split("/", 2);
        String due = "dummy";
        if (dueSplit.length > 1) {
            due = dueSplit[1].split(" ", 2)[1];
        }
        String[] doneMarkers = dueSplit[0].split(" ", 2);
        String userCommand = doneMarkers[0].toLowerCase();
        String taskDescription = "dummy";
        if (doneMarkers.length > 1) {
            taskDescription = doneMarkers[1];
        }

        this.userCommand = userCommand;
        this.due = due;
        this.taskDescription = taskDescription;
    }

    public String getUserCommand() {
        return userCommand;
    }

    public String getDue() {
        return due;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
