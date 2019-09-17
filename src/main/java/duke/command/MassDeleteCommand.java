package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;
import java.util.Collections;
import java.util.ArrayList;

public class MassDeleteCommand extends Command {

    private ArrayList<Integer> indexes;
    private String errorMessage = "";
    private ArrayList<Task> deletedTasks;

    /**
     * Constructor which takes in the keyword to be deleted.
     * @param message The keyword.
     */
    public MassDeleteCommand(String message) {
        super(message);
        deletedTasks = new ArrayList<Task>();
        indexes = new ArrayList<Integer>();
        String[] index = message.split(" ");
        for (String string : index) {
            indexes.add(Integer.parseInt(string));
        }
    }

    /**
     * Method to sort the indexes.
     */
    public void sortIndex() {
        Collections.reverse(indexes);
    }

    /**
     * Method to remove the duplicates in the indexes.
     * @return ArrayList of integers without duplicates.
     */
    public ArrayList<Integer> removeDuplicate(ArrayList<Integer> array) {
        ArrayList<Integer> output = new ArrayList<Integer>();
        for (Integer no : array) {
            if (!output.contains(no)) {
                output.add(no);
            }
        }
        return output;
    }

    /**
     * Used to execute the command and modify the list of tasks accordingly.
     *
     * @param listOfTasks List of tasks to be modified according to the input.
     * @param storage     Used to modify the files in the hard drive.
     * @param ui          Prints out all the messages.
     * @throws Exception For when there are any errors when executing the method.
     */
    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (listOfTasks.isEmpty()) {
            errorMessage = "The list is empty, there is nothing to delete!";
            return;
        }
        sortIndex();
        indexes = removeDuplicate(indexes);
        for (Integer no : indexes) {
            if (no > listOfTasks.size() || no <= 0) {
                errorMessage = "Sorry but such task does not exist!";
                return;
            }
        }
        for (Integer no : indexes) {
            deletedTasks.add(listOfTasks.get(no - 1));
            listOfTasks.getTasks().remove(no - 1);
        }
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("")) {
            return errorMessage;
        } else {
            String output = "";
            output += "Success! I have deleted the following tasks:\n\n";
            for (int i = deletedTasks.size() - 1; i >= 0; i--) {
                if (i == 0) {
                    output += deletedTasks.get(i).toString();
                } else {
                    output += deletedTasks.get(i).toString();
                    output += "\n";
                }
            }
            return output;
        }
    }
}
