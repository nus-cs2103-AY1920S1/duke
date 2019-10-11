/**
 * A class to handle User Interactions.
 */

public class Ui {
    /**
     * Method to print task and number of tasks.
     * @param count number of tasks in the list
     * @param t type of task
     * @return String representation of task and number of tasks.
     */
    public String printTask(int count, Task t) {
        assert count > 0 : "Number of tasks cannot be negative.";
        if (count == 1) {
            return "  " + t + "\nNow you have " + count + " task in the list.";
        } else {
            return "  " + t + "\nNow you have " + count + " tasks in the list.";
        }
    }

}
