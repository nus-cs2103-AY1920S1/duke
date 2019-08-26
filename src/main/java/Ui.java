
/**
 * prints all the messages to console
 */
public class Ui {
    /**
     * prints duke logo
     */
    public void welcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I do for you?");
    }
    /**
     * prints task added confirmation
     * @param list task list to get the added task
     */
    public void taskAdded(TaskList list) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(list.get(list.list.size() - 1));
        System.out.println("Now you have " + list.list.size() + " tasks in the list.");
    }
    /**
     * prints goodbye message
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    /**
     * prints task set as done confirmation
     * @param list task list
     * @param item task index in list to be set as done
     */
    public void done(TaskList list, int item) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(item));
    }
    /**
     * prints out full task list
     * @param list
     */
    public void list(TaskList list) {
        System.out.print(list);
    }
    /**
     * displays delete task confirmation
     * @param t the task that was deleted.
     */
    public void delete(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
    }

	public void found(TaskList matchingTasks) {
        if(matchingTasks.list.size() == 0){
            System.out.println("oh, fiddlesticks! there are no tasks matching your search!");
        }
        else{
            System.out.println("Here are the matching tasks in your list:");
            System.out.print(matchingTasks);
        }
	}
}