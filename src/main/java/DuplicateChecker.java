import java.util.ArrayList;

class DuplicateChecker {

    private ArrayList<Task> taskList;
    private Ui ui = new Ui();

    DuplicateChecker(){}

    DuplicateChecker(ArrayList<Task> taskList){
        this.taskList = taskList;
    }

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
