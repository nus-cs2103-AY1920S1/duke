package duke.command;

import duke.task.TaskList;

import java.util.ArrayList;

/**
 * A command to find tasks in a TaskList that matches the searchKey.
 */
public class FindCommand extends Command {
    private String searchKey;

    /**
     * Constructs a FindCommand with the searchKey.
     * @param searchKey The searchKey that will be used to identify matching tasks.
     */
    public FindCommand(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * Returns a string of tasks from the TaskList that has a description that matches the searchKey.
     * @param tasks The TaskList to search tasks from.
     * @return A string of tasks that matches the searchKey.
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<String> matchList = new ArrayList<>();
        addMatchingTasks(matchList, tasks);
        return createStringOfMatchedTasks(matchList);
    }

    private void addMatchingTasks(ArrayList<String> matchList, TaskList tasks) {
        int totalNumOfTasks = tasks.getSizeOfTaskList();
        for (int i = 0; i < totalNumOfTasks; i++) {
            String taskInfo = tasks.getTaskDsc(i);
            addToListIfFoundMatch(taskInfo, matchList);
        }
    }

    private void addToListIfFoundMatch(String taskInfo, ArrayList<String> matchList) {
        if (taskInfo.contains(this.searchKey)) {
            matchList.add(taskInfo);
        }
    }

    private String createStringOfMatchedTasks(ArrayList<String> matchList) {
        StringBuilder sb = new StringBuilder();
        int lenOfMatchedList = matchList.size();
        sb.append("Here are the matching tasks in your list");
        for (int i = 1; i <= lenOfMatchedList; i++) {
            sb.append("\n");
            sb.append(i);
            sb.append(". ");
            sb.append(matchList.get(i - 1));
        }
        return sb.toString();
    }
}
