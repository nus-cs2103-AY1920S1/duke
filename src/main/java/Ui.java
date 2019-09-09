import java.util.ArrayList;

/**
 * prints all the messages to console
 */
public class Ui {
    /**
     * returns duke logo
     */
    public static String welcome() {
        String logo = " ____        _        \n" 
                    + "|  _ \\ _   _| | _____ \n" 
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n" 
                    + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello, I'm Duke. " + "What can I do for you?";
    }

    /**
     * returns task added confirmation
     * 
     * @param list task list to get the added task
     */
    public String taskAdded(TaskList list) {
        return "Got it. I've added this task: \n" + list.get(list.list.size() - 1) + "\nNow you have "
                + list.list.size() + " tasks in the list.";
    }

    /**
     * returns goodbye message
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * returns task set as done confirmation
     * 
     * @param list task list
     * @param item task index in list to be set as done
     */
    public String done(TaskList list, int item) {
        return "Nice! I've marked this task as done:\n" + list.get(item);
    }

    /**
     * returns out full task list
     * 
     * @param list
     */
    public String list(TaskList list) {
        if(list.list.size() == 0){
            return "oh bother! there aren't any tasks in the list!";
        }
        return list + "";
    }

    /**
     * returns delete task confirmation
     * 
     * @param t the task that was deleted.
     */
    public String delete(Task t) {
        return "Noted. I've removed this task:\n" + t;
    }

    /**
     * returns list of tasks
     * 
     * @param matchingTasks
     */
    public String found(ArrayList<TaskWithOrder> matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "oh, fiddlesticks! there are no tasks matching your search!";
        } else {
            String res = "Here are the matching tasks in your list:\n";
            for (TaskWithOrder t : matchingTasks){
                res+= t + "\n";
            }
            return res;
        }
    }
}