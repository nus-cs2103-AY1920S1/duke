import java.util.ArrayList;
import java.util.Scanner;

class DuplicateChecker {

    private ArrayList<Task> taskList;
    private Ui ui = new Ui();
    //private Scanner scan = new Scanner(System.in);

    DuplicateChecker(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

    boolean checkDuplicate (Task task){

        for (Task value : taskList) {
            if (value.description.equals(task.description)) {
                ui.printToConsoleAndGui("You already have a similar task\n" + value.toString() +
                        "\nwould you like to add anyway? (y/n)\n");
                //String reply = scan.next();
                //return !reply.equals("y");
                return true;
            }
        }
        return false;
    }
}
