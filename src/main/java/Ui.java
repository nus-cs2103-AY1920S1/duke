import javafx.scene.layout.AnchorPane;
import java.util.LinkedList;

/**
 * To deals with interactions with the user.
 */
public class Ui extends AnchorPane {
    /**
     * Printing the list of task from a taskList.
     *
     * @param taskList the list of tasks
     * @return an output text for the UI.
     */
    public String printList(TaskList taskList) {
        String outputText = "Here are the matching tasks in your list:\n";
        int counter = 1;

        for (Task subTask : taskList.getListOfTasks()) {
            outputText = (outputText + counter + ". " + subTask) + "\n";
            counter++;
        }

        return outputText;
    }

    /**
     * To find the task that matches the keyword with the description.
     *
     * @param taskList a list of tasks
     * @param keyword the text/keyword that will be searched from the list
     * @return an output text for the UI.
     */
    public String searchTaskKeyword(TaskList taskList, String keyword) {
        int counter = 1;
        int typoCounter = 1;
        boolean hasMatchResult = false;
        boolean hasPartialMatchResult = false;
        char[] charOfKeyword = keyword.toCharArray();
        String outputText = "Here are the matching tasks in your list:\n";
        String typoInputReplyText = "Did you mean by this?\n";

        String firstLetter = Character.toString(charOfKeyword[0]);
        String lastLetter = Character.toString(charOfKeyword[charOfKeyword.length - 1]);

        for (Task subTask : taskList.getListOfTasks()) {
            if (subTask.getDescription().contains(keyword)) {
                outputText = (outputText + counter + ". " + subTask) + "\n";
                counter++;
                hasMatchResult = true;
            }

            String[] listOfWords = subTask.getDescription().split(" ");

            for (String word : listOfWords) {
                if (word.startsWith(firstLetter) && word.endsWith(lastLetter)) {
                    typoInputReplyText = (typoInputReplyText + typoCounter + ". " + subTask) + "\n";
                    typoCounter++;
                    hasPartialMatchResult = true;
                    break;
                }
            }
        }

        if (!hasPartialMatchResult) {
            for (Task subTask : taskList.getListOfTasks()) {
                if (subTask.getDescription().startsWith(firstLetter)) {
                    typoInputReplyText = (typoInputReplyText + typoCounter + ". " + subTask) + "\n";
                    typoCounter++;
                    hasPartialMatchResult = true;
                }
            }
        }

        if (hasMatchResult) {
            return outputText;
        } else if (hasPartialMatchResult) {
            return typoInputReplyText;
        } else {
            return "No result found!";
        }
    }

    /**
     * Prints out the task that is marked as done.
     *
     * @param selectedTask a selected task from a list of tasks.
     * @return an output text for the UI.
     */
    public String printTaskDone(Task selectedTask) {
        String outputText = "";
        outputText = "Nice! I've marked this task as done: "
                + "\n"
                + "[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription();
        return outputText;
    }

    /**
     * Adds a newTask into the current taskList.
     *
     * @param taskList a list of tasks
     * @param newTask a new task to be added to the list of tasks
     * @return an output text for the UI.
     */
    public String printAddTask(TaskList taskList, Task newTask) {
        String outputText = "";
        outputText = "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + taskList.getListOfTasks().size() + " tasks in the list.";
        return outputText;
    }


    /**
     * To delete a task at a certain location.
     *
     * @param deletedTask a LinkedList that stores the list of tasks.
     * @param index the location of the task to be deleted.
     * @return an output text for the UI.
     */
    public String printTaskDelete(LinkedList<Task> deletedTask, int index) {
        String outputText = "";
        outputText = "Noted. I've removed this task:\n"
                + deletedTask.get(index - 1) + "\n"
                + "Now you have " + (deletedTask.size() - 1) + " tasks in the list.";
        return outputText;
    }
}
