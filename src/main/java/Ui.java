import java.util.ArrayList;

public class Ui {

    public void startOfInteractions() {
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");
    }

    public void endOfInteractions() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(ArrayList<Task> task, int counter) {
        String isPlural = counter == 1 ? "is" : "are";
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Here " + isPlural + " the " + taskIfPlural + " in your list:");
        for (int i = 1; i <= counter; i++) {
            System.out.println(i + "." + task.get(i - 1));
        }
    }

    public void taskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public int printAddedTask(ArrayList<Task> task, int counter) {
        System.out.println("Got it. I've added this task:\n" + task.get(counter));
        counter++;
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
        return counter;
    }

    public void deleteTask(Task task, int counter) {
        String taskIfPlural = counter <= 1 ? "task" : "tasks";
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
    }
}