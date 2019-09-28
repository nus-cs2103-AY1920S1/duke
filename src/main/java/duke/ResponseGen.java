package duke;

import duke.task.Task;

import java.util.List;

public class ResponseGen {

    public ResponseGen() {}

    public static String addResponse(List<Task> listOfTasks) {
        String output = "";
        output += "\tGot it. I've added this task:\n";
        output += "\t  " + listOfTasks.get(listOfTasks.size() - 1) + '\n';
        output += "\tNow you have " + listOfTasks.size() + " tasks in the list.";
        return output;
    }

    public static String deleteResponse (List<Task> listOfTasks, Task removed) {
        String output = "";
        output += "\tNoted. I've removed this task:\n";
        output += "\t  " + removed.toString() + '\n';
        output += "\tNow you have " + listOfTasks.size() + " tasks in the list.";
        return output;
    }

    public static String doneResponse(List<Task> listOfTasks, int index) {
        listOfTasks.get(index - 1).done = true;
        String output = "";
        output += "\tNice! I've marked this task as done:\n";
        output += "\t  [✓] " + listOfTasks.get(index - 1).name;
        return output;
    }

    public static String findResponse(List<Task> listOfTasks) {
        StringBuilder output = new StringBuilder();
        if (listOfTasks.size() == 0) {
            return "None of the tasks match!";
        } else {
            for (int j = 0; j < listOfTasks.size(); j++) {
                output.append('\t');
                output.append(j + 1).append(".");
                output.append(listOfTasks.get(j).toString()).append('\n');
            }
            return output.toString();
        }
    }

    public static String listResponse(List<Task> listOfTasks) {
        StringBuilder output = new StringBuilder();
        if (listOfTasks.size() == 0) {
            return "You have nothing on your to-do list!";
        } else {
            output.append("\tHere are the tasks in your list:\n");
            for (int j = 0; j < listOfTasks.size(); j++) {
                output.append('\t');
                output.append(j + 1).append(".");
                output.append(listOfTasks.get(j).toString()).append('\n');
            }
            output.append("\tYou have ").append(listOfTasks.size()).append(" tasks!");
            return output.toString();
        }
    }

}
