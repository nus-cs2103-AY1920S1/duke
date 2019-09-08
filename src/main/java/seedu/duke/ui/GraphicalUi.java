package seedu.duke.ui;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;

public class GraphicalUi extends Ui {
    public GraphicalUi(){
    }

    public static String getWelcomeString(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro =
                "Hello! I'm Duke " + "\n"
                + "What can I do for you?" + "\n";
        return (logo + intro);
    }

    public String getDoneSequence(TaskList tasks, int taskNum){
        String output = "Nice! I've marked this task as done:\n"
                + "[" + tasks.getTask(taskNum).getStatusIcon() + "] " + tasks.getTask(taskNum).getTaskName()
                + "\n" ;
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getTodoSequence(TaskList tasks, Todo newTodo){
        String output = "Got it. I've added this task:\n"
                + newTodo.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getDeadlineSequence(TaskList tasks, Deadline newDeadline){
        String output = "Got it. I've added this task:\n"
                + newDeadline.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getEventSequence(TaskList tasks, Event newEvent) {
        String output = "Got it. I've added this task:\n"
                + newEvent.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getDeleteSequence(TaskList tasks, Task taskToDelete) {
        String output = "Noted. I've removed this task.\n"
                + taskToDelete.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getByeSequence() {
        String output = "\n" + "Bye. Hope to see you again soon!" + "\n";
        return (output);
    }

    /**
     * Returns the string containing tasks in a pre-formatted form.
     * Eg. "1.[E][X] Run (at: ERC)
     *      2.[D][V] IPPT (by: 21st of December 2004, 8.15am)
     *      3.[E][V] Lecture (at: LT7A)".
     *
     * @param tasks TaskList object
     * @return String of tasks
     */
    public String getListSequence(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            output += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    public String getFoundTasks(TaskList tasks) {
        String output = "Here are the matching tasks in your list:\n";
        output += getListSequence(tasks);
        return (output);
    }

    public String getTasksRemainingSequence(int numOfTaskRemaining) {
        String output = "\nNow you have "
                + numOfTaskRemaining + " tasks in the list.\n";
        return output;
    }
}
