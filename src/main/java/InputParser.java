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

        String by;
        String desc;
        int taskNumber;
        String at;
        String firstWord;
        Ui ui = new Ui();
        String guidedUserInterfaceMsg;
        InputSplitter inputSplitter = new InputSplitter(input);
        final String MSG_LIST = "Here are the tasks in your list:\n";
        final String MSG_FIND = "Here are the matching tasks in your list:\n";
        final String ERROR_FIND = "OOPS!!! Incorrect format for the 'find' command.\n";
        final String MSG_UNKNOWN = "OOPS!!! I'm sorry, but I don't know what that means\n";

        firstWord = input.split(" ")[0];
        switch (firstWord) {

        case "bye":
            break;

        case "todo":
            desc = inputSplitter.splitInput("todo")[0];
            ToDo toDo = new ToDo(desc);
            modifyTaskList.addToTaskList(taskList, toDo, Duke.Action.ADD);
            break;


        case "deadline":
            desc = inputSplitter.splitInput("deadline")[0];
            by = inputSplitter.splitInput("deadline")[1];
            Deadline d = new Deadline(desc, by);
            modifyTaskList.addToTaskList(taskList, d, Duke.Action.ADD);
            break;

        case "event":
            desc = inputSplitter.splitInput("event")[0];
            at = inputSplitter.splitInput("event")[1];
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
            taskNumber = Integer.parseInt(inputSplitter.splitInput("done")[0]);
            modifyTaskList.changeTaskList(taskList, taskNumber - 1, Duke.Action.DONE);
            break;

        case "delete":
            taskNumber = Integer.parseInt(inputSplitter.splitInput("delete")[0]);
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
            ui.printToConsoleAndGui(MSG_UNKNOWN);
            break;
        }
    }
}
