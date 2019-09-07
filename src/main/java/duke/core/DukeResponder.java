package duke.core;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.TaskList;

public class DukeResponder {

    /**
     * Show welcome message.
     * Often used in the beginning of the program.
     */
    public String getWelcomeMessage() {
        return buildMultilineString(new String[] {
                "Hello! I'm Duke",
                "What can I do for you?"
        });
    }

    /**
     * Show all tasks.
     * This method would format the list of tasks
     * and display them neatly.
     *
     * @param tasks the list of tasks to be shown
     */
    public String getTasksMessage(TaskList tasks) {
        return buildMultilineString(tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new));
    }

    /**
     * Show notification of successful completion.
     * This method would notify the user that the task
     * is being marked as done.
     *
     * @param task the task that is completed
     */
    public String getTaskDoneMessage(Task task) {
        return buildMultilineString(new String[] {
                "Nice! I've marked this task as done:",
                task.toString()
        });
    }

    /**
     * Show notification of successful deletion.
     * This method would notify the user that the task
     * is successfully deleted.
     *
     * @param task the task that is deleted
     * @param tasks the list of tasks left after deletion
     */
    public String getTaskDeletedMessage(Task task, TaskList tasks) {
        return buildMultilineString(new String[] {
                "Noted. I've removed this task:",
                task.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
        });
    }

    /**
     * Show notification of successful addition.
     * This method would notify the user that the task
     * is successfully added.
     *
     * @param task the task that is added
     * @param tasks the list of tasks left after addition
     */
    public String getTaskAddedMessage(Task task, TaskList tasks) {
        return buildMultilineString(new String[] {
                "Got it. I've added this task:",
                task.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
        });
    }

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
