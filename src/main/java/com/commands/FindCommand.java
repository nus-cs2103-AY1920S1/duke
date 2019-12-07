package com.commands;

import com.TaskList;
import com.exceptions.DukeException;
import com.tasks.Task;
import com.util.Storage;
import com.util.StaticStrings;
import com.util.ui.*;
import gui.GUIUi;
import com.util.stats.DukeStatistics;

import java.util.ArrayList;

public class FindCommand extends Command {

    /**
     * List of words to find from tasks' descriptions.
     */
    protected ArrayList<String> keywords;

    public FindCommand(ArrayList<String> keywords) {
        super("find");
        this.keywords = keywords;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) {
        // No tasks to search through
        if (taskList.getNumTasks() == 0) {
            ui.showMessage(StaticStrings.NO_TASKS);
        }
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (String keyword : keywords) {
            for (Task currTask : taskList.getTaskArr()) {
                boolean alreadyFound = matchingTasks.contains(currTask);
                if (!alreadyFound && currTask.containsKeyword(keyword)) {
                    matchingTasks.add(currTask);
                }
            }
        }
        if (matchingTasks.size() == 0) {
            ui.showNoTasksMatchingKeywordResponse();
        } else {
            ui.showFindKeywordResponse(matchingTasks);
        }
        new DukeStatistics().addLog(this, keywords);
    }

}
