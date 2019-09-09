/**
 * A class to handle User Interactions.
 */

public class Ui {

    public String printTask(int count, Task t) {
        if (count == 1) {
            return "  " + t + "\nNow you have " + count + " task in the list.";
        } else {
            return "  " + t + "\nNow you have " + count + " tasks in the list.";
        }
    }

}
