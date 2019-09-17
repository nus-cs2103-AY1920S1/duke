import java.util.List;


/**
 * Used to parse commands that are given.
 */
public class Parser {
    private List availableCommands;

    public Parser(List availableCommands) {
        this.availableCommands = availableCommands;
    }

    /**
     * Provides the ability to parse the String commands and execute the commands accordingly.
     *
     * @param ui       UI related methods
     * @param storage  Storage related methods
     * @param command  command that is being passed in
     * @param taskList The list that store all the task.
     * @throws DukeException Exception in case of error
     */
    public String parseCommand(Ui ui, Storage storage, String command, TaskList taskList) throws DukeException {
        if (!availableCommands.contains(command.split(" ")[0])) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (command.equals("bye")) {
            System.exit(0);
        } else if (command.equals("list")) {
            return ui.printList(taskList);
        } else if (command.startsWith("done")) {
            assert command != null : "Task should not be null";
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            String stringBuilder = taskList.doneTask(index);
            storage.saveTask();
            return stringBuilder;
        } else if (command.startsWith("delete")) {
            assert command != null : "Task should not be null";
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            String stringBuilder = taskList.deleteTask(index);
            storage.saveTask();
            return stringBuilder;
        } else if (command.startsWith("find")) {
            List findList;
            String[] words = command.split("\\s");
            String findString = words[1];
            findList = taskList.findTask(taskList, findString);
            return ui.printTaskList(findList);
        } else {
            assert command != null : "Task should not be null";
            String stringBuilder = taskList.addTask(command);
            storage.saveTask();
            return stringBuilder;
        }
        return null;
    }
}
