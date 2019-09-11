package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private static final StringBuilder indent = new StringBuilder(" ".repeat(5));
    private static final StringBuilder horizontalLine = new StringBuilder(indent + "_".repeat(50) + "\n");
    private StringBuilder content = new StringBuilder();

    /**
     * Read input each time the user type in.
     * @return a string of the input
     */
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }

        return null;
    }

    /**
     * Add exception message to output string.
     * @param e a Duke exception
     */
    public void showException(DukeException e) {
        this.content.append(indent);
        this.content.append(String.format("Oops! %s\n", e.getMessage()));
    }

    /**
     * Add greeting message to output string.
     */
    public void showGreeting() {
        String greeting = "     Hello! I'm Duke\n"
                + "     What can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Add message to output string when there is no task in the task list.
     */
    public void showNoTask() {
        this.content.append(indent);
        this.content.append("There is currently no task in the list!\n");
    }

    /**
     * Add message and task information to output string when there are tasks in the task list.
     */
    public void showTasks(TaskList taskList) {
        this.content.append(indent);
        this.content.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            this.content.append(indent);
            this.content.append(String.format("     %d.%s\n", i + 1, taskList.getTask(i)));
        }
    }

    /**
     * Add exit message to output string.
     */
    public void showExit() {
        this.content.append(indent);
        this.content.append("Bye. Hope to see you again soon!\n");
    }

    /**
     * Add done message to output string.
     * @param task a task to be marked as done
     */
    public void showDone(Task task) {
        this.content.append(indent);
        this.content.append("Nice! I've marked this task as done:\n");
        this.content.append(indent);
        this.content.append(task + "\n");
    }

    /**
     * Add delete message to output string.
     * @param task a task to be deleted from the task list
     */
    public void showDelete(Task task, TaskList taskList) {
        this.content.append(indent);
        this.content.append("Noted. I've removed this task:\n");
        this.content.append(indent);
        this.content.append(task + "\n");
        this.content.append(indent);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    /**
     * Add add-task message to output string.
     * @param task a task to be added into the task list
     */
    public void showAddTask(Task task, TaskList taskList) {
        this.content.append(indent);
        this.content.append("Got it. I've added this task:\n");
        this.content.append(indent);
        this.content.append(String.format("  %s\n", task));
        this.content.append(indent);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    /**
     * Print the output string as a response to user.
     */
    public void print() {
        StringBuilder output = new StringBuilder();
        output.append(horizontalLine);
        output.append(content);
        output.append(horizontalLine);
        System.out.print(output.toString());
        this.content = new StringBuilder();
    }
}
