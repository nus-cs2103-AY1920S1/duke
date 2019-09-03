package duke;

import java.util.Scanner;

class Ui {
    private Scanner scanner = new Scanner(System.in);

    String getInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }

        return null;
    }

    String showWelcome() {
        return "Hello! I'm Duke. What can I do for you?";
    }

    String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    String showTasks(String tasks) {
        return String.format("Here are your tasks:%n%s", tasks);
    }

    String showQuery(String tasks) {
        return String.format("Here are the matching tasks in your list:%n%s", tasks);
    }

    String showTaskDeletion(Task deletedTask) {
        return String.format("Done! I have deleted the following task:%n%s", deletedTask.toString());
    }

    String showTaskDone(Task doneTask) {
        return String.format("Done! I have marked the following task as done:%n%s", doneTask.toString());
    }

    String showTaskAdded(Task newTask) {
        return String.format("Done! I have added the following task:%n%s", newTask.toString());
    }

    String showException(DukeException e) {
        return String.format("Error:%n%s", e.getMessage());
    }
}
