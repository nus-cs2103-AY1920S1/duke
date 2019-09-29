package duke.handler;

import duke.task.Task;

import java.util.Scanner;

/**
 * Ui is the face of Duke, taking in inputs from users and also
 * displaying template messages of Duke.
 */
public class Ui {

    private Scanner scanner;
    private String response;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.response = "";
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        response = "Hello from\n" + logo + "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(response);
    }

    public void showFarewell() {
        scanner.close();
        setResponse("Bye. Hope to see you again soon!");
        System.out.println(response);
    }

    public void showTasksMessage() {
        setResponse("Here are the tasks in your list:");
        System.out.println(response);
    }

    public void showTaskAdded(Task addedTask, int numberOfTasks) {
        setResponse("Got it. I've added this task:");
        setResponse(addedTask.toString());
        setResponse(String.format("Now you have %d tasks in the list.", numberOfTasks));
        System.out.println(response);
    }

    public void showTaskDeleted(Task deletedTask, int numberOfTasks) {
        setResponse("Noted. I've removed this task:");
        setResponse(deletedTask.toString());
        setResponse(String.format("Now you have %d tasks in the list.", numberOfTasks));
        System.out.println(response);
    }

    public void showTaskDone(Task completedTask) {
        setResponse("Nice! I've marked this task as done:");
        setResponse(completedTask.toString());
        System.out.println(response);
    }

    public void showMatchingTasksMessage() {
        setResponse("Here are the matching tasks in your list:");
        System.out.println(response);
    }

    public void showMatchingTasks(Task matchedTask, int taskIndex) {
        setResponse(taskIndex + "." + matchedTask);
        System.out.println(response);
    }

//    public void showLine() {
//        setResponse("_______________________________");
//        System.out.println("_______________________________");
//    }

    public void showError(String error) {
        clearResponse();
        setResponse(error);
        System.out.println(error);
    }

    public void showLoadingError() {
        clearResponse();
        setResponse("☹ OOPS!!! File could not be found.");
        System.out.println(response);
    }

    public void setResponse(String line) {
        response += line + "\n";
    }

    public String getResponse() {
        return response;
    }

    public void clearResponse() {
        response = "";
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
