package utils;

import java.util.ArrayList;

import tasks.Task;

public class Ui {

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

    public String getTasksAsString(ArrayList<Task> tasks) {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += "\n\t" + (i + 1) + ". " + tasks.get(i).toString();
        }

        return output;
    }

    public String getLoadingError() {
        String output = "";
        output += getTopBorder();
        output += "\n\tSorry! There was an error loading the files from the system.";
        output += getBottomBorder();

        return output;
    }

}