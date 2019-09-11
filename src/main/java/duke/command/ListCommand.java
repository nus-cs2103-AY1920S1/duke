package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        int totalNumOfTask = tasks.getSizeOfTaskList();
        return createListMsg(tasks, totalNumOfTask);
    }

    private String createListMsg(TaskList tasks, int totalNumOfTask) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Here are the tasks in your list:");
        for (int i = 1; i <= totalNumOfTask; i++) {
            returnString.append("\n");
            returnString.append(i);
            returnString.append(". ");
            returnString.append(tasks.getFullTaskInfo(i - 1));
        }
        return returnString.toString();
    }
}
