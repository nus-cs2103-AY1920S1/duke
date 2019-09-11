package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final StringBuilder indent = new StringBuilder(" ".repeat(5));
    private static final StringBuilder horizontalLine = new StringBuilder(indent + "_".repeat(50) + "\n");
    private StringBuilder content = new StringBuilder();

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }

        return null;
    }

    public void showException(DukeException e) {
        this.content.append(indent);
        this.content.append(String.format("Oops! %s\n", e.getMessage()));
    }

    public void showGreeting() {
        String greeting = "     Hello! I'm Duke\n"
                + "     What can I do for you?";
        System.out.println(greeting);
    }

    public void showNoTask() {
        this.content.append(indent);
        this.content.append("There is currently no task in the list!\n");
    }

    public void showTasks(TaskList taskList) {
        this.content.append(indent);
        this.content.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            this.content.append(indent);
            this.content.append(String.format("     %d.%s\n", i + 1, taskList.getTask(i)));
        }
    }

    public void showExit() {
        this.content.append(indent);
        this.content.append("Bye. Hope to see you again soon!\n");
    }

    public void showDone(Task task) {
        this.content.append(indent);
        this.content.append("Nice! I've marked this task as done:\n");
        this.content.append(indent);
        this.content.append(task + "\n");
    }

    public void showDelete(Task task, TaskList taskList) {
        this.content.append(indent);
        this.content.append("Noted. I've removed this task:\n");
        this.content.append(indent);
        this.content.append(task + "\n");
        this.content.append(indent);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    public void showAddTask(Task task, TaskList taskList) {
        this.content.append(indent);
        this.content.append("Got it. I've added this task:\n");
        this.content.append(indent);
        this.content.append(String.format("  %s\n", task));
        this.content.append(indent);
        this.content.append(String.format("Now you have %d tasks in the list.\n", taskList.getSize()));
    }

    public void showNoMatchingTask() {
        this.content.append(indent);
        this.content.append("There is no matching task in the list!\n");
    }

    public void showMatchingTasks(List<Task> selectedTasks) {
        this.content.append(indent);
        this.content.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < selectedTasks.size(); i++) {
            this.content.append(indent);
            this.content.append(String.format("     %d.%s\n", i + 1, selectedTasks.get(i)));
        }
    }

    public void print() {
        StringBuilder output = new StringBuilder();
        output.append(horizontalLine);
        output.append(content);
        output.append(horizontalLine);
        System.out.print(output.toString());
        this.content = new StringBuilder();
    }
}
