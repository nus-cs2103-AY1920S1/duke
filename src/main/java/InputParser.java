import java.io.IOException;
import java.util.ArrayList;

/**
 * The class used to handle the logic for user inputs
 */

public class InputParser {

    private ArrayList<Task> taskList;
    private ModifyTaskList modifyTaskList = new ModifyTaskList();

    /**
     * The constructor for the InputParser class.
     * @param taskList  Used to store task object information.
     */

    protected InputParser(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Used to determine which method from the {@link ModifyTaskList} class to call
     * depending on the first word from the input.
     *
     * @param input  The input from the user.
     * @see ModifyTaskList
     */

    protected void determineAction(String input) throws IOException {

        int taskNumber;
        String by;
        String desc;
        String at;
        String firstWord;
        Ui ui = new Ui();
        String guidedUserInterfaceMsg;
        final String ERROR_TODO = "OOPS!!! The description of a todo cannot be empty.\n";
        final String ERROR_DEADLINE = "OOPS!!! Incorrect description for event; remember to use the /by keyword.\n";
        final String ERROR_EVENT = "OOPS!!! Incorrect description for event; remember to use the /at keyword.\n";
        final String MSG_LIST = "Here are the tasks in your list:\n";
        final String ERROR_INVALID_NUM = "OOPS!!! Please enter a valid number\n";
        final String MSG_FIND = "Here are the matching tasks in your list:\n";
        final String ERROR_FIND = "OOPS!!! Incorrect format for the 'find' command.\n";
        final String MSG_UNKNOWN = "OOPS!!! I'm sorry, but I don't know what that means\n";

        firstWord = input.split(" ")[0];
        switch (firstWord) {

        case "bye":
            break;

        case "todo":
            try {
                desc = input.split(" ", 2)[1];
            } catch (IndexOutOfBoundsException err) {
                System.out.println(ERROR_TODO);
                ui.setGuidedUserInterfaceMsg(ERROR_TODO);
                break;
            }
            ToDo toDo = new ToDo(desc);
            modifyTaskList.addToTaskList(taskList, toDo, Duke.Action.ADD);
            break;


        case "deadline":
            try {
                by = input.split(" /by ")[1];
                desc = input.split(" /by ")[0].split(" ", 2)[1];
            } catch (IndexOutOfBoundsException err) {
                System.out.println(ERROR_DEADLINE);
                ui.setGuidedUserInterfaceMsg(ERROR_DEADLINE);
                break;
            }
            Deadline d = new Deadline(desc, by);
            modifyTaskList.addToTaskList(taskList, d, Duke.Action.ADD);
            break;

        case "event":
            try {
                at = input.split(" /at ")[1];
                desc = input.split(" /at ")[0].split(" ", 2)[1];
            } catch (IndexOutOfBoundsException err) {
                System.out.println(ERROR_EVENT);
                ui.setGuidedUserInterfaceMsg(ERROR_EVENT);
                break;
            }
            Event e = new Event(desc, at);
            modifyTaskList.addToTaskList(taskList, e, Duke.Action.ADD);
            break;

        case "list":
            System.out.println(MSG_LIST);
            guidedUserInterfaceMsg = MSG_LIST;

            for (int a = 0; a < taskList.size(); a++) {
                System.out.println((a + 1) + ". " + taskList.get(a).toString());
                guidedUserInterfaceMsg += (a + 1) + ". " + taskList.get(a).toString() + "\n";
            }
            ui.setGuidedUserInterfaceMsg(guidedUserInterfaceMsg);
            System.out.println();
            break;

        case "done":
            try {
                taskNumber = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException err) {
                ui.setGuidedUserInterfaceMsg(ERROR_INVALID_NUM);
                System.out.println(ERROR_INVALID_NUM);
                break;
            }
            modifyTaskList.changeTaskList(taskList, taskNumber - 1, Duke.Action.DONE);
            break;

        case "delete":
            try {
                taskNumber = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException err) {
                System.out.println(ERROR_INVALID_NUM);
                ui.setGuidedUserInterfaceMsg(ERROR_INVALID_NUM);
                break;
            }
            modifyTaskList.changeTaskList(taskList, taskNumber - 1, Duke.Action.REMOVE);
            break;

        case "find":
            try {
                desc = input.split(" ")[1];
                int findCounter = 0;
                System.out.println(MSG_FIND);
                guidedUserInterfaceMsg = MSG_FIND;
                for (int a = 0; a < taskList.size(); a++) {
                    if (taskList.get(a).description.contains(desc)) {
                        findCounter++;
                        System.out.println(findCounter + ". " + taskList.get(a).toString());
                        guidedUserInterfaceMsg += findCounter + ". " + taskList.get(a).toString() +"\n";
                    }
                }
                ui.setGuidedUserInterfaceMsg(guidedUserInterfaceMsg);
                break;
            } catch (IndexOutOfBoundsException err) {
                System.out.println(ERROR_FIND);
                ui.setGuidedUserInterfaceMsg(ERROR_FIND);
                break;
            }

        default:
            System.out.println(MSG_UNKNOWN);
            ui.setGuidedUserInterfaceMsg(MSG_UNKNOWN);
            break;
        }
    }
}
