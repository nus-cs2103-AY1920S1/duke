import java.util.List;

/**
 * Manages UI related methods.
 */
public class UI {

    public UI() {
    }

    public void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Greetings when the program starts
     */
    public void greet() {
        horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
        System.out.println();
    }

    /**
     * Closing when the program ends
     */
    public void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    /**
     * Prints all the tasks that are inside the list, according to the format given.
     * @param taskList The list that stores all the task
     */
    public void printList(TaskList taskList) {
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ".[" + taskList.getTask(i).getType() + "]" + "[" + taskList.getTask(i).getStatusIcon() + "] " + taskList.getTask(i).getDescription() + taskList.getTask(i).getDate());
        }
        horizontalLine();
        System.out.println();
    }

    public void printTaskList(List<Task> findList) {
        horizontalLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            System.out.println(i+1 + ".[" + findList.get(i).getType() + "]"+ "[" + findList.get(i).getStatusIcon() + "] " + findList.get(i).getDescription() + findList.get(i).getDate());
        }
        horizontalLine();
        System.out.println();
    }


}
