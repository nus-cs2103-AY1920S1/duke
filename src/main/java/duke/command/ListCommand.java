package duke.command;

import duke.task.TaskList;

/**
 * A command to list all the tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Returns c in the TaskList currently.
     *
     * @param tasks The TaskList to execute the command on.
     * @return A string that lists all the tasks.
     */
    @Override
    public String execute(TaskList tasks) {
        int totalNumOfTask = tasks.getSizeOfTaskList();
        return createListMsg(tasks, totalNumOfTask);
    }

    private String createListMsg(TaskList tasks, int totalNumOfTask) {
        StringBuilder returnString = new StringBuilder();
        returnString.append("Here are the tasks in your list:");
        for (int i = 1; i <= totalNumOfTask; i++) {
            String currTaskInfo = tasks.getFullTaskInfo(i - 1);
            returnString.append(
                    createALineForListMsg(
                            currTaskInfo,
                            i
                    )
            );
        }
        return returnString.toString();
    }

    private StringBuilder createALineForListMsg(String taskInfo, int currentIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(currentIndex);
        sb.append(". ");
        sb.append(taskInfo);
        return sb;
    }
}
