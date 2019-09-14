import java.util.ArrayList;

/**
 * The class used to check for duplicate tasks within the taskList
 */

class DuplicateChecker {

    private ArrayList<Task> taskList;
    private Ui ui = new Ui();


    /**
     * The constructor for the DuplicateChecker class.
     * @param taskList  Used to store task object information.
     */
    
    DuplicateChecker(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    /**
     * Checks for duplicates in the description of tasks and warns the user if present.
     *
     * @param task  The new task given by the user.
     * @return true or false depending if there is a duplicate
     */

    boolean checkDuplicate (Task task){

        for (Task value : taskList) {
            if (value.description.equals(task.description)) {
                ui.printToConsoleAndGui("You already have a similar task\n" + value.toString() +
                        "\nIf intended, try renaming the task to be more specific");
                return true;
            }
        }

        return false;
    }
}
