import java.io.IOException;
import java.util.ArrayList;

/**
 * The class used to handle the logic for user inputs
 */

public class InputParser {

    private ArrayList<Task> taskList;
    private TaskListModifier taskListModifier = new TaskListModifier();

    /**
     * The constructor for the InputParser class.
     * @param taskList  Used to store task object information.
     */

    protected InputParser(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Determines which method from the {@link TaskListModifier} class to call
     * depending on the first word from the input.
     *
     * @param input  The input from the user.
     * @see TaskListModifier
     */

    protected void determineAction(String input) throws IOException {

        String by;
        String desc;
        int taskNumber;
        String at;
        String firstWord;
        Ui ui = new Ui();
        InputSplitter inputSplitter = new InputSplitter(input);
        OutputLister outputLister = new OutputLister(taskList);
        final String MSG_UNKNOWN = "OOPS!!! I'm sorry, but I don't know what that means\n";

        firstWord = input.split(" ")[0];
        switch (firstWord) {

        case "bye":
            break;

        case "todo":
            desc = inputSplitter.splitInput("todo")[0];
            ToDo toDo = new ToDo(desc);
            taskListModifier.addToTaskList(taskList, toDo, Duke.Action.ADD);
            break;


        case "deadline":
            desc = inputSplitter.splitInput("deadline")[0];
            by = inputSplitter.splitInput("deadline")[1];
            Deadline d = new Deadline(desc, by);
            taskListModifier.addToTaskList(taskList, d, Duke.Action.ADD);
            break;

        case "event":
            desc = inputSplitter.splitInput("event")[0];
            at = inputSplitter.splitInput("event")[1];
            Event e = new Event(desc, at);
            taskListModifier.addToTaskList(taskList, e, Duke.Action.ADD);
            break;

        case "list":
            outputLister.listTasks();
            break;

        case "done":
            taskNumber = Integer.parseInt(inputSplitter.splitInput("done")[0]);
            taskListModifier.changeTaskList(taskList, taskNumber - 1, Duke.Action.DONE);
            break;

        case "delete":
            taskNumber = Integer.parseInt(inputSplitter.splitInput("delete")[0]);
            taskListModifier.changeTaskList(taskList, taskNumber - 1, Duke.Action.REMOVE);
            break;

        case "find":
            desc = inputSplitter.splitInput("find")[0];
            outputLister.findTasks(desc);
            break;

        default:
            ui.printToConsoleAndGui(MSG_UNKNOWN);
            break;
        }
    }
}
