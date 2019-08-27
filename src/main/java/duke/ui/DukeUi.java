package duke.ui;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import duke.tasklist.TaskList;

/**
 * Represents the User Interface of the Duke App.
 * Lists the messages that are printed out when a corresponding command is executed.
 */
public class DukeUi {
    public DukeUi() {

    }

    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void printByeMessage() {
        System.out.println("Bye! Hope to see you soon! :)");
    }

    public void printAddDeadlineMessage(Deadline current, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(current.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void printTaskDeletedMessage(Task current, int numberOfTasks) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(current.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void printTaskDoneMessage(TaskList tasks, int taskNumber) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber - 1).toString());
    }

    public void printAddEventMessage(Event current, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(current.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
    }

    public void printAddTodoMessage(Todo current, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(current.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Prints out tasks that contains the keyWord specified
     * @param task TaskList that contains tasks to check from.
     * @param keyWord keyWord to check if task description contains the keyWord.
     */
    public void printKeyWordTasks(TaskList task, String keyWord) {
        System.out.println("Here are the matching tasks in your list:");
        for(int i = 0; i < task.size(); i++) {
            Task current = task.get(i);
            if(current.getDescription().contains(keyWord)) {
                System.out.println(current.toString());
            }
        }
    }
}
