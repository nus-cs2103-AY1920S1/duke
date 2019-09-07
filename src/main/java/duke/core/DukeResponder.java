package duke.core;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

public class DukeResponder {

    /**
     * Generates welcome message.
     * This method returns the welcome message.
     *
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return buildMultilineString(new String[] {
            "Hello! I'm Duke",
            "What can I do for you?"
        });
    }

    /**
     * Generates tasks message.
     * This method returns a neatly formatted tasks listing.
     *
     * @param tasks list of tasks to be shown.
     * @return tasks message
     */
    public String getTasksMessage(TaskList tasks) {
        return buildMultilineString(tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new));
    }

    /**
     * Generates task done message.
     * This method returns a message to indicate that a task is marked as done.
     *
     * @param task completed task
     * @return task done message
     */
    public String getTaskDoneMessage(Task task) {
        return buildMultilineString(new String[] {
            "Nice! I've marked this task as done:",
            task.toString()
        });
    }

    /**
     * Generates task deleted message.
     * This method returns a message to indicate that a task is successfully deleted.
     *
     * @param task deleted task
     * @param tasks list of tasks after deletion
     * @return task deleted message
     */
    public String getTaskDeletedMessage(Task task, TaskList tasks) {
        return buildMultilineString(new String[] {
            "Noted. I've removed this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Generates task added message.
     * This method returns a message to indicate that a task is successfully added.
     *
     * @param task added task
     * @param tasks list of tasks after addition
     * @return task added message
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        return buildMultilineString(new String[] {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Generates error message.
     * This method returns a message reporting the error occurred.
     *
     * @param e instance of {@link DukeException}
     * @return error message
     */
    public String getErrorMessage(DukeException e) {
        return buildMultilineString(new String[] {
            e.getResponseMessage()
        });
    }

    private String buildMultilineString(String[] strings) {
        assert strings.length > 0 : "strings should not be empty";

        StringBuilder builder = new StringBuilder();
        builder.append(strings[0]);

        for (int i = 1; i < strings.length; i++) {
            builder.append("\n");
            builder.append(strings[i]);
        }

        return builder.toString();
    }

}
