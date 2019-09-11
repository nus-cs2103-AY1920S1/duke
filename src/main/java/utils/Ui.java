package utils;

import tasks.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Returns a welcome message.
     */
    public String getWelcomeMessage() {
        String output = "";

        // Welcome message
        output += getTopBorder();
        output += "\n\tHello! I'm Duke!";
        output += "\n\tWhat can I do for you?\n";
        output += getBottomBorder();

        return output;
    }

    public String getTopBorder() {
        return "____________________________________________________________";
    }

    public String getBottomBorder() {
        return "\n____________________________________________________________";
    }

    /**
     * Returns the list of tasks as a String.
     */
    public String getTasksAsString(ArrayList<Task> tasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n\t").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return output.toString();
    }
}
