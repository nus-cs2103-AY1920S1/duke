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

    void showWelcome() {
        print("Hello! I'm Duke. What can I do for you?");
    }

    void showGoodbye() {
        print("Bye. Hope to see you again soon!");
    }

    void showTasks(String tasks) {
        print(String.format("Here are your tasks:%n%s", tasks));
    }

    void showQuery(String tasks) {
        print(String.format("Here are the matching tasks in your list:%n%s", tasks));
    }

    void showTaskDeletion(Task deletedTask) {
        print(String.format("Done! I have deleted the following task: %s", deletedTask.toString()));
    }

    void showTaskDone(Task doneTask) {
        print(String.format("Done! I have marked the following task as done: %s", doneTask.toString()));
    }

    void showTaskAdded(Task newTask) {
        print(String.format("Done! I have added the following task: %s", newTask.toString()));
    }

    void showException(DukeException e) {
        printError(e.getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }

    private void printError(String message) {
        System.out.println(String.format("Error: %s", message));
    }
}
