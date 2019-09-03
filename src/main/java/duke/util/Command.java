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
            "1) list: view your Todo list\n" +
            "2) done {task_number}: mark a task as done\n" +
            "3) delete {task_number}: delete a task\n" +
            "4) find {query}: find tasks containing {query}\n\n" +
            "          ---Task Commands---\n\n" +
            "5) todo {task_name}\n" +
            "6) event {event_name} /at {dd/mm/yyyy} {time_in_24hr}\n" +
            "7) deadline {task_name} /by {dd/mm/yyyy} {time_in_24hr}";

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
