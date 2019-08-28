import java.util.LinkedList;

public class Ui {
    public Ui () {
    }

    public void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("File loaded unsuccessful");
    }

    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;

        for (Task subTask : taskList.getListOfTasks()) {
            System.out.println(counter + ". " + subTask);
            counter++;
        }
    }

    public void printTaskDone(Task selectedTask) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + selectedTask.getStatusIcon() + "] " + selectedTask.getDescription());
    }

    public void printAddTask(TaskList taskList, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.getListOfTasks().size() + " tasks in the list.");
    }


    public void printTaskDelete(LinkedList<Task> deletedTask, int index) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask.get(index - 1));
        System.out.println("Now you have " + (deletedTask.size() - 1) + " tasks in the list.");
    }
}
