package duke;

import java.util.List;

public class MessageGenerator {

    Formatter formatter = new Formatter();

    /**
     * Generates messages for different user input.
     */
    public MessageGenerator() {}

    /**
     * @return String for header of task list.
     */
    private String listTasks() {
        return "Here are the tasks in your list: ";
    }

    /**
     * @return String for header of removing task action.
     */
    private String removeTask() {
        return "Noted. I've removed this task: ";
    }

    /**
     * @return String for header of adding task action.
     */
    private String addTask() {
        return "Got it. I've added this task: ";
    }

    /**
     * @return String for header of marking task as done.
     */
    private String markDone() {
        return "Nice! I've marked this task as done:";
    }

    /**
     * @return String to indicate number of tasks in list.
     */
    private String numTask(int n) {
        return "Now you have " + n + " task(s) in the list.";
    }

    /**
     * @return String to greet user.
     */
    private String greeting() {
        return "Hello I'm Duke\n" + "What can I do for you?";
    }

    /**
     * @return String when program is exited.
     */
    private String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Formats task with proper indentations.
     * @return String for formatted task.
     */
    private String formatTask(Task task) {
        if (task == null) {
            return "";
        } else {
            return "  " + task.toString();
        }
    }

    /**
     * Prints the program response when a task is removed.
     * @param task Task that is to be removed.
     * @param n Number tagged to task.
     */
    void printRemove(Task task, int n) {
        formatter.printFormat(removeTask(), formatTask(task), numTask(n));
    }

    /**
     * Prints the program when tasks are added.
     * @param task Task that is to be added.
     * @param n Number tagged to task.
     */
    void printAdd(Task task, int n) {
        formatter.printFormat(addTask(), formatTask(task), numTask(n));
    }

    /**
     * Prints the program response when a task is set as done.
     * @param task Task that is set as done.
     */
    void printDone(Task task) {
        formatter.printFormat(markDone(), formatTask(task));
    }

    /**
     * Prints the program response when user asks for list of tasks.
     * @param list List of tasks in their string form.
     */
    void printList(List<String> list) {
        formatter.printFormat(listTasks(), list);
    }

    /**
     * Prints welcome message when program begins.
     */
    public void greet() {
        formatter.printFormat(greeting());
    }

    /**
     * Prints goodbye message when program terminates.
     */
    public void bye() {
        formatter.printFormat(byeString());
    }

}
