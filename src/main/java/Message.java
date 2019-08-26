public class Message {
    /**
     * Prints out message after successful deletion of task
     * @param task - Task that has been successfully deleted
     * @param size - Current size of list
     */
    public static void printSuccessfulAddMessage(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints out message after successful marking of task
     * @param task - Task that has been successfully marked done
     */
    public static void printSuccessfulDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints out message after successful deletion of task
     * @param task - Task that has been successfully deleted
     * @param size - Current size of list
     */
    public static void printSuccessDeleteTaskMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
