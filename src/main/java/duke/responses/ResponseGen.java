package duke.responses;

import duke.task.Task;

import java.util.List;

public class ResponseGen {

    public ResponseGen() {
    }

    /**
     * Response Generator for add command.
     * @param listOfTasks list of tasks.
     * @return output string.
     */
    public static String addResponse(List<Task> listOfTasks) {
        String output = "";
        output += "Got it. I've added this task:\n";
        output += listOfTasks.get(listOfTasks.size() - 1).toString() + '\n';
        output += "Now you have " + listOfTasks.size() + " tasks in the list.";
        return output;
    }

    /**
     * Response generator for delete response.
     * @param listOfTasks list of tasks.
     * @param removed removed task.
     * @return output string.
     */
    public static String deleteResponse(List<Task> listOfTasks, Task removed) {
        String output = "";
        output += "Noted. I've removed this task:\n";
        output +=  removed.toString() + '\n';
        output += "Now you have " + listOfTasks.size() + " tasks in the list.";
        return output;
    }

    /**
     * Response generator for done response.
     * @param listOfTasks list of tasks.
     * @param index index of task to set as done.
     * @return output string.
     */
    public static String doneResponse(List<Task> listOfTasks, int index) {
        listOfTasks.get(index - 1).done = true;
        String output = "";
        output += "Nice! I've marked this task as done:\n";
        output += "[\u2713] " + listOfTasks.get(index - 1).name;
        return output;
    }

    /**
     * Response generator for find response.
     * @param listOfTasks list of tasks.
     * @return output string.
     */
    public static String findResponse(List<Task> listOfTasks) {
        StringBuilder output = new StringBuilder();
        if (listOfTasks.size() == 0) {
            return "None of the tasks match!";
        } else {
            for (int j = 0; j < listOfTasks.size(); j++) {
                output.append(j + 1).append(".");
                output.append(listOfTasks.get(j).toString()).append('\n');
            }
            return output.toString();
        }
    }

    /**
     * Response generator for list command.
     * @param listOfTasks list of tasks.
     * @return output string.
     */
    public static String listResponse(List<Task> listOfTasks) {
        StringBuilder output = new StringBuilder();
        if (listOfTasks.size() == 0) {
            return "You have nothing on your to-do list!";
        } else {
            output.append("Here are the tasks in your list:\n");
            for (int j = 0; j < listOfTasks.size(); j++) {
                output.append(j + 1).append(".");
                output.append(listOfTasks.get(j).toString()).append('\n');
            }
            output.append("You have ").append(listOfTasks.size()).append(" tasks!");
            return output.toString();
        }
    }

    /**
     * Response Generator for sort command.
     * @param listOfTasks list of tasks.
     * @return output string.
     */
    public static String sortResponse(List<Task> listOfTasks) {
        StringBuilder output = new StringBuilder();
        if (listOfTasks.size() == 0) {
            return "You have nothing on your to-do list!";
        } else {
            output.append("Here are the tasks in your list sorted alphabetically:\n");
            for (int j = 0; j < listOfTasks.size(); j++) {
                output.append(j + 1).append(".");
                output.append(listOfTasks.get(j).toString()).append('\n');
            }
            output.append("You have ").append(listOfTasks.size()).append(" tasks!");
            return output.toString();
        }
    }

}
