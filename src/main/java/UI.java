import java.util.List;

/**
 * Manages UI related methods.
 */
public class UI {

    public UI() {
    }

    public String horizontalLine() {
        return "__________________________________________________";
    }

    /**
     * Greetings when the program starts
     */
    public String greet() {
        /*horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        horizontalLine();
        System.out.println();*/
        String holder = horizontalLine() + "\nHello I'm Duke\n" + "What can I do for you?\n" + horizontalLine();
        return holder;
    }

    /**
     * Prints message when the program ends
     */
    public String exit() {
        String holder = horizontalLine() + "\nBye. Hope to see you again soon!\n" + horizontalLine();
        return holder;
    }

    /**
     * Prints all the tasks that are inside the list, according to the format given.
     * @param taskList The list that stores all the task
     */
    public String printList(TaskList taskList) {
        String holder = horizontalLine() + "\nHere are the tasks in your list:\n";
        String holder2 = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            holder2 += i + 1 + ".[" + taskList.getTask(i).getType() + "]" + "[" + taskList.getTask(i).getStatusIcon() + "] " + taskList.getTask(i).getDescription() + taskList.getTask(i).getDate() + "\n";
        }
        holder = holder + holder2 + horizontalLine();
        return holder;
    }

    public String printTaskList(List<Task> findList) {
        String holder = horizontalLine() + "\nHere are the matching tasks in your list:\n";
        String holder2 = "";
        for (int i = 0; i < findList.size(); i++) {
            holder2 += i+1 + ".[" + findList.get(i).getType() + "]"+ "[" + findList.get(i).getStatusIcon() + "] " + findList.get(i).getDescription() + findList.get(i).getDate() + "\n";
        }
        holder = holder + holder2 + horizontalLine();
        return holder;
    }


}
