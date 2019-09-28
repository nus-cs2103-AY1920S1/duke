package duke.ui;

import java.util.List;

import duke.exception.DukeException;
import duke.parser.Aliases;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private static final StringBuilder INDENT = new StringBuilder(" ".repeat(5));
    private StringBuilder content = new StringBuilder();

    /**
     * Add exception message to output string.
     * @param e a Duke exception
     */
    public void showException(DukeException e) {
        this.content.append(INDENT);
        this.content.append(String.format("Oops! %s\n", e.getMessage()));
    }

    /**
     * Add greeting message to output string.
     */
    public void showGreeting() {
        this.content.append(INDENT);
        this.content.append("Hello! I'm Duke\n");
        this.content.append(INDENT);
        this.content.append("What can I do for you?");
        assert this.content.toString().equals("     Hello! I'm Duke\n     What can I do for you?") : "Welcome "
                + "message is wrong!";
    }

    /**
     * Add message to output string when there is no task in the task list.
     */
    public void showNoTask() {
        this.content.append(INDENT);
        this.content.append("There is currently no task in the list!\n");
    }

    /**
     * Add message and task information to output string when there are tasks in the task list.
     */
    public void showTasks(TaskList taskList) {
        this.content.append(INDENT);
        this.content.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            this.content.append(INDENT);
            this.content.append(String.format("     %d.%s\n", i + 1, taskList.getTask(i)));
        }
    }

    /**
     * Add exit message to output string.
     */
    public void showExit() {
        this.content.append(INDENT);
        this.content.append("Bye. Hope to see you again soon!\n");
    }

    /**
     * Add done message to output string.
     * @param task a task to be marked as done
     */
    public void showDone(Task task) {
        this.content.append(INDENT);
        this.content.append("Nice! I've marked this task as done:\n");
        this.content.append(INDENT);
        this.content.append(task + "\n");
    }

    /**
     * Add delete message to output string.
     * @param task a task to be deleted from the task list
     */
    public void showDelete(Task task, TaskList taskList) {
        this.content.append(INDENT);
        this.content.append("Noted. I've removed this task:\n");
        this.content.append(INDENT);
        this.content.append(task + "\n");
        this.content.append(INDENT);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    /**
     * Add add-task message to output string.
     * @param task a task to be added into the task list
     */
    public void showAddTask(Task task, TaskList taskList) {
        this.content.append(INDENT);
        this.content.append("Got it. I've added this task:\n");
        this.content.append(INDENT);
        this.content.append(String.format("  %s\n", task));
        this.content.append(INDENT);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    /**
     * Add message to output string when there is no task matching the given keyword.
     */
    public void showNoMatchingTask() {
        this.content.append(INDENT);
        this.content.append("There is no matching task in the list!\n");
    }

    /**
     * Add message and task information of all matching tasks to output string when there are tasks in the task list.
     */
    public void showMatchingTasks(List<Task> selectedTasks) {
        this.content.append(INDENT);
        this.content.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < selectedTasks.size(); i++) {
            this.content.append(INDENT);
            this.content.append(String.format("     %d.%s\n", i + 1, selectedTasks.get(i)));
        }
    }

    /**
     * Add add-alias message to output string.
     * @param alias alias entered by the user
     * @param keyword keyword mapped to the alias
     */
    public void showAddAlias(String alias, String keyword) {
        this.content.append(INDENT);
        this.content.append(String.format("\"%s\" is now mapped to \"%s\".\n", alias, keyword));
    }

    /**
     * Add delete alias message to output string.
     * @param alias alias entered by the user
     * @param keyword keyword mapped to the alias
     */
    public void showDeleteAlias(String alias, String keyword) {
        this.content.append(INDENT);
        this.content.append(String.format("\"%s\" is no longer mapped to \"%s\".\n", alias, keyword));
    }

    /**
     * Add view alias message to output string.
     * @param alias alias entered by the user
     * @param keyword keyword mapped to the alias
     */
    public void showViewAlias(String alias, String keyword) {
        this.content.append(INDENT);
        this.content.append(String.format("\"%s\" is an alias for \"%s\".\n", alias, keyword));
    }

    /**
     * Add list aliases message to output string.
     * @param aliases the aliases object of the application
     */
    public void showAllAliases(Aliases aliases) {
        this.content.append(INDENT);
        this.content.append("Here are the available aliases (left) and their mapped keywords (right):\n");
        for (String alias: aliases.getAllAliases()) {
            this.content.append(INDENT);
            this.content.append(String.format("\"%s\": \"%s\".\n", alias, aliases.getKeyword(alias)));
        }
    }

    /**
     * Add list aliases message to output string when there is no alias available.
     */
    public void showNoAliases() {
        this.content.append(INDENT);
        this.content.append("There is no alias available, try create some!\n");
    }

    /**
     * Add list matching aliases message to output string.
     * @param matchingAliases aliases mapped to the keyword
     * @param keyword keyword entered by the user
     */
    public void showMatchingAliases(List<String> matchingAliases, String keyword) {
        this.content.append(INDENT);
        this.content.append(String.format("Here are the aliases for \"%s\":\n", keyword));
        this.content.append(INDENT);
        this.content.append(String.join(", ", matchingAliases));
        this.content.append('\n');
    }

    /**
     * Add message to output string when there is no alias mapped the given keyword.
     * @param keyword keyword entered by the user
     */
    public void showNoMatchingAliases(String keyword) {
        this.content.append(INDENT);
        this.content.append(String.format("There is no alias available for \"%s\", try create some!\n", keyword));
    }

    /**
     * Convert the message to a string.
     * @return a String containing all response displayed to user.
     */
    @Override
    public String toString() {
        String output = content.toString();
        this.content = new StringBuilder();
        return output;
    }
}
