package duke;

import duke.task.Task;

public class Ui {
    public static String showBye() {
        return "Bye! Hope to see you again soon :-)";
    }

    public static String showTasks(TaskList tasks) {
        return String.format("%s%n%s", "Here are your tasks:", tasks.toString());
    }

    public static String showDeletedTask(Task task) {
        return String.format("%s%n%s", "Done! I have deleted the following task:", task.toString());
    }

    public static String showDoneTask(Task task) {
        return String.format("%s%n%s", "Great! I have marked the following task as done:", task.toString());
    }

    public static String showFindMatches(TaskList tasks) {
        return String.format("%s%n%s", "Done! I have found the following tasks matching your query:", tasks.toString());
    }

    public static String showFindNoMatch() {
        return "Oh no! No task matching your query was found :-(";
    }

    public static String showChangedSaveFile(String fileName) {
        return String.format("%s%n%s", "Done! I have switched to the following save file:", fileName);
    }

    public static String showAddedTask(Task task) {
        return String.format("%s%n%s", "Done! I have successfully added the following task:", task.toString());
    }

    static String showInvalidCommand() {
        return "Invalid command, please try again!";
    }

    static String showError(Exception e) {
        return String.format("%s%n%s", "Error:", e.getMessage());
    }
}
