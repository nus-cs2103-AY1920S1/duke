package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an user interface. A <code>Ui</code> handles the display of messages
 * and the incoming user input.
 */
public class Ui {
    private Scanner scanner;
    private MainWindow window;

    Ui(MainWindow window) {
        this.scanner = new Scanner(System.in);
        this.window = window;
    }

    /**
     * Prints a message when there is no saved data.
     */
    public void getInfoMessage(ListStoreModel lists) {
        String taskMessage = "There is no saved task list. I've created a new one for you.";
        String peopleMessage = "You have no loans or debts! :)";
        if (lists.taskList.isEmpty() && lists.peopleList.isEmpty()) {
            print(taskMessage + " " + peopleMessage);
        } else if (lists.taskList.isEmpty()) {
            print(taskMessage);
        } else if (lists.peopleList.isEmpty()) {
            print(peopleMessage);
        }
    }

    /**
     * Prints an error to the console.
     * @param error error to be printed.
     */
    void showError(String error) {
        print(error);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____          _        \n"
                    + "|   _   \\ _   _|  |  _____  \n"
                    + "|  |  |   |  |  |  |  |/  /  _   \\\n"
                    + "|  |_|   |  |_|  |    <   __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
        print("Hello from\n" + logo);

        greet();
    }

    /**
     * Reads the next line of user input.
     * @return raw unprocessed string of user input.
     */
    String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a greeting message to the user.
     */
    public void greet() {
        String message = "Hello! I'm Duke.\nWhat can I do for you?";
        print(message);
    }

    /**
     * Formats a regular string into a Message.
     * @param message string to be converted.
     */
    public void print(String message) {
        List<String> list = new ArrayList<>();
        list.add(message);
        this.window.handleDukeResponse(new Message(list).toString());
    }

    /**
     * Prints a regular string into the GUI.
     * @param message string to be rendered.
     */
    public void print(Message message) {
        this.window.handleDukeResponse(message.toString());
    }
}
