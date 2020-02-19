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
        output += "\n\tHello! I'm Patrick!";
        output += "\n\tWhat can I do for you?";

        return output;
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
