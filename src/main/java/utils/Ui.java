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
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n\t").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return output.toString();
    }

    public String getAddCommandOutput(Task t, TaskList list) {
        String output = "";
        output += getTopBorder();
        output += "\n\tGot it! I've added this task: ";
        output += "\n\t" + t.toString();
        output += "\n\tNow you have " + list.getSize() + " tasks in the list.";
        output += getBottomBorder();
        return output;
    }
}
