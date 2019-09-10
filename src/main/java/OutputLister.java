import java.util.ArrayList;

/**
 * The class used to list output
 */

public class OutputLister {

    private ArrayList<Task> taskList;
    private Ui ui = new Ui();
    private String userInterfaceMsg;
    private final String MSG_LIST = "Here are the tasks in your list:\n";
    private final String MSG_FIND = "Here are the matching tasks in your list:\n";

    OutputLister(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    /**
     * Lists all of the Tasks in the taskList
     */

    void listTasks (){

        userInterfaceMsg = MSG_LIST;

        for (int a = 0; a < taskList.size(); a++) {
            userInterfaceMsg += (a + 1) + ". " + taskList.get(a).toString() + "\n";
        }
        ui.printToConsoleAndGui(userInterfaceMsg);
        }

    /**
     * Lists all of the Tasks in the taskList that contain the description
     * @param desc Used to find tasks that contain it
     */

    void findTasks (String desc) {

        userInterfaceMsg = MSG_FIND;
        int findCounter = 0;
        for (int a = 0; a < taskList.size(); a++) {
            if (taskList.get(a).description.contains(desc)) {
                findCounter++;
                userInterfaceMsg += findCounter + ". " + taskList.get(a).toString() + "\n";
            }
        }
        ui.printToConsoleAndGui(userInterfaceMsg);
    }
}
