import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class is created to make sense of the User commands and input.
 */
public class Parser {
    private TaskList tasksList;
    private Ui ui;

    /**
     * Parser class is instantiated by passing TaskList and Ui class parameters.
     * @param tasksList TaskList class.
     * @param ui Ui class.
     */
    protected Parser(TaskList tasksList, Ui ui) {
        this.tasksList = tasksList;
        this.ui = ui;
    }

    /**
     * Making sense of the User's input, and to throw an IllegalCommandException when User enter an invalid
     * command.
     * @param command Inputs of the User.
     */
    protected String parse(String command) {
        try {
            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                this.tasksList.addTask(command);
                return ui.getAddedMessage(this.tasksList.getTaskList());
            } else if (command.contains("delete")) {
                String deletedTask = this.tasksList.deleteTask(command); // retrieve the deleted task.
                 return ui.getDeletedMessage(this.tasksList.getTaskList(), deletedTask);
            } else if (command.contains("done")) {
                String taskDoneStr = this.tasksList.doneTask(command);  // retrieve the task that is done.
                return ui.getDoneMessage(taskDoneStr);
            } else if (command.contains("list")) {
                return ui.showList(this.tasksList.getTaskList());
            } else if (command.contains("find")) {
                ArrayList<Task> foundTask = this.tasksList.findTasks(command);
                return ui.showFoundMessage(foundTask);
            } else if (command.contains("view")) {
                ArrayList<Task> viewTaskOnDay = this.tasksList.viewTasks(command);
                return ui.showViewMessage(viewTaskOnDay);
            } else {
                return "I'm sorry, but I don't know what that means :-(";
            }
        } catch (IllegalCommandException errorMsg) {
            return errorMsg.toString();
        }
    }

    /**
     * To retrieve the taskList.
     * @return The stored taskList.
     */
    protected ArrayList<Task> retrieveTasks() {
        return this.tasksList.getTaskList();
    }
}
