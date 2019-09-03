package duke.util;

import java.io.IOException;

public class Command {
    private static final int BYE = 0;
    private static final int LIST = 1;
    private static final int DONE = 2;
    private static final int DELETE = 3;
    private static final int FIND = 4;
    private static final int TASK = 5;
    private static final int COMMANDS = -1;
    private static final String list =
            "1) list - view your Todo list\n" +
            "2) done TASK_NUMBER - mark a task as done\n" +
            "3) delete TASK_NUMBER - delete a task\n" +
            "4) find 'QUERY' - find tasks containing 'QUERY'\n" +
            "5) todo 'TASK_NAME' - create a new todo called 'TASK_NAME'\n" +
            "6) event 'EVENT_NAME' /at 'dd/mm/yyyy' 'TIME_IN_24HR'\n" +
            "7) deadline 'TASK_NAME' /by /dd/mm/yyyy 'TIME_IN_24HR'";

    private String[] inputParts;
    private int command;

    public Command(String[] inputParts, int command) {
        this.command = command;
        this.inputParts = inputParts;
    }

    public String execute(Storage storage, Ui ui, TaskList tasks) throws IOException, DukeException {
        if (command == BYE) {
            storage.saveHistory(tasks.getTaskList());
            return ui.byeMessage();
        } else if (command == LIST){
            return tasks.getListAsString();
        } else if (command == DONE){
            String result = tasks.markItemComplete(Integer.parseInt(inputParts[1]));
            storage.saveHistory(tasks.getTaskList());
            return result;
        } else if (command == DELETE){
            String result = tasks.deleteItem(Integer.parseInt(inputParts[1]));
            storage.saveHistory(tasks.getTaskList());
            return result;
        } else if (command == FIND){
            return tasks.findItem(inputParts[1]);
        } else if (command == TASK){
            String result = tasks.registerNewTask(inputParts);
            storage.saveHistory(tasks.getTaskList());
            return result;
        } else if (command == COMMANDS){
            return list;
        }
        return "";
    }
}
