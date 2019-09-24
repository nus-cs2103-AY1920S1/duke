package duke.ui;

import duke.task.Deadline;
import duke.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles the chat bot's responses to the user, namely what it should print back to the user.
 */

public class Ui {
    public static final String BYE_RESPONSE = "Goodbye, Master William. Hope to see you again soon.";
    public static final String NO_TASKS_RESPONSE = "There are no tasks for now.";
    public static final String DUKE_EXCEPTION = "I'm terribly sorry, but I don't know what that means.";
    public static final String NO_DEADLINES_RESPONSE = "There are no deadlines for now.";;
    public static final String EMPTY_DESCRIPTION_RESPONSE = "The description cannot be empty.";
    public static final String LISTING_RESPONSE = "Here are the tasks in your list:\n";
    public static final String NO_INDEX_RESPONSE = "The task index does not exist.";
    public static final String TYPE_SORT_RESPONSE = "Here are your tasks sorted by type:\nDeadline, Event, Todo\n";
    public static final String DEADLINE_SORT_RESPONSE = "Here are your tasks sorted by deadlines:\n";


    /**
     * Prints the loading error message if Duke fails to load properly.
     */
    public void showLoadingError() {
        System.out.println("Duke has failed to load properly.");
    }

    public String addDeadlineResponse(Task deadlineTask, ArrayList<Task> tasks) {
        String response = "Got it. I've added this task:\n    " + deadlineTask + "\nNow you have " + tasks.size()
                + " task(s) in the " + "list.";
        return response;
    }

    public String deleteTaskResponse(Task deletedTask, ArrayList<Task> tasks) {
        String response = "Noted. I've deleted the following task:\n    " + deletedTask + "\nNow you have " + tasks.size()
                + " task(s) in the " + "list.";
        return response;
    }

    public String doneTaskResponse(Task task) {
        String response = "Very good. I've marked this task as done:\n    " + task;
        return response;
    }

    public String addTodoTaskResponse(Task toDoTask, ArrayList<Task> tasks) {
        String response = "Got it. I've added this task:\n    " + toDoTask + "\nNow you have " + tasks.size()
                + " task(s) in the " + "list.";
        return response;
    }

    public String listOfDeadlines(ArrayList<Deadline> deadlines, StringBuilder sb) {
        for (int i = 0; i < deadlines.size(); i++) {
            int k = i + 1;
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(k);
            sb.append(". ");
            sb.append(deadlines.get(i));
        }
        String response = sb.toString();
        return response;
    }

    public String listOfTasks(ArrayList<Task> tasks, StringBuilder sb) {
        for (int i = 0; i < tasks.size(); i++) {
            int k = i + 1;
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(k);
            sb.append(". ");
            sb.append(tasks.get(i));
        }
        String response = sb.toString();
        return response;
    }
}