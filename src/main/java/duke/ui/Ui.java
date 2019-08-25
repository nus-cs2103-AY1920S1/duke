package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    public void sendLine() {
        System.out.println("____________________________________________________________");
    }

    public void showCompletedTask(Task task) {
        sendLine();
        sendMessage("Nice! I've marked this task as done:");
        sendMessage("  " + task.toString());
        sendLine();
    }

    public void showDeletedTask(Task task) {
        sendLine();
        sendMessage("Noted. I've removed this task: ");
        sendMessage("  " + task.toString());
        sendMessage("Now you have " +  TaskList.getNumberOfTasks() + " tasks in the list.");
        sendLine();
    }

    public void showAddedTask(Task task) {
        sendLine();
        sendMessage("Got it. I've added this task: ");
        sendMessage("  " + task.toString());
        sendMessage("Now you have " + TaskList.getNumberOfTasks() + " tasks in the list");
        sendLine();
    }

    public void sendGreeting() {
        sendLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" I'm created by @seanlowjk");
        System.out.println(" What can I do for you?");
        sendLine();
    }

    public void sendFarewell() {
        sendLine();
        System.out.println(" Bye. Hope to see you again soon!");
        sendLine();
    }

    public void sendMessage(String input) {
        System.out.println(" " + input);
    }

    public void sendErrorMessage(DukeException error) {
        sendLine();
        sendMessage(error.toString());
        sendLine();
    }

    public void showLoadingError() {
        sendLine();
        System.out.println(" OOPS !!! Loading Error! Please reboot...");
        sendLine();
    }

    public void listTasks() {
        sendLine();
        sendMessage("Here are the tasks in your list:");
        for (int tasknum = 0; tasknum < TaskList.getNumberOfTasks(); tasknum ++) {
            Task task = TaskList.getTask(tasknum);
            String todo = task.toString();
            if (task.isCompleted) {
                sendMessage((tasknum + 1) + "." + todo);
            } else {
                sendMessage((tasknum + 1) + "." + todo);
            }
        }
        sendLine();
    }
}
