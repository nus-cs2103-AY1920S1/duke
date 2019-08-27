import java.util.List;


/**
 * Used to parse commands that are given.
 */
public class Parser {
    private List<String> availableCommands;

    public Parser(List availableCommands) {
        this.availableCommands = availableCommands;
    }

    /**
     * Provides the ability to parse the String commands and execute the commands accordingly.
     * @param ui UI related methods
     * @param storage Storage related methods
     * @param command command that is being passed in
     * @param taskList The list that store all the task.
     * @throws DukeException Exception in case of error
     */
    public void parseCommand (UI ui, Storage storage, String command, TaskList taskList) throws DukeException {
        if (!availableCommands.contains(command.split(" ")[0])) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else if (command.equals("bye")) {
            ui.exit();
            System.exit(0);
        } else if (command.equals("list")) {
            ui.printList(taskList);
        } else if (command.startsWith("done")) {
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            taskList.doneTask(index);
            storage.saveTask();
        } else if (command.startsWith("delete")) {
            String[] words = command.split("\\s");
            int index = Integer.parseInt(words[1]);
            taskList.deleteTask(index);
            storage.saveTask();
        } else if(command.startsWith("find")) {
            List<Task> findList;
            String[] words = command.split("\\s");
            String findString = words[1];
            findList = taskList.findTask(taskList, findString);
            ui.printTaskList(findList);
        } else {
            taskList.addTask(command);
            storage.saveTask();
        }
    }


}
