package com.util.stats;

import com.commands.Command;
import com.tasks.Task;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DukeStatistics {

    private StatsStorage statsStorage;
    private ArrayList<Log> executionLogs;

    //private static Logger logger = Logger.getLogger("DukeStatistics");

    public DukeStatistics() {
        statsStorage = new StatsStorage("F:\\CS2103\\duke\\data\\dukeStats.txt");
        executionLogs = new ArrayList<Log>(statsStorage.load());
    }

    public void addLog(Command command, Task task, ArrayList<String> keywords) {
        Log newLog = new Log(command.getCommandWord(),
                task == null ? null : task.toString(),
                keywords == null ? null : keywords);
        executionLogs.add(newLog);
        //logger.log(Level.INFO, "New execution log added");
        //logger.log(Level.INFO, "Number of logs now: " + getNumLogs());
        statsStorage.save(executionLogs);
    }

    /**
     * For commands that did not need any descriptions.
     * @param command
     */
    public void addLog(Command command) {
        addLog(command, null, null);
    }

    /**
     * For commands that modified the list of tasks.
     * @param command
     * @param task Task that was involved
     */
    public void addLog(Command command, Task task) {
        addLog(command, task, null);
    }

    /**
     * For command 'find'.
     * @param command
     * @param keywords List of keywords that was searched
     */
    public void addLog(Command command, ArrayList<String> keywords) {
        addLog(command, null, keywords);
    }

   public int getNumLogs() {
        return executionLogs.size();
   }

    // TODO number of tasks mark done
    // TODO so far
    // TODO in the past week/ in every week
    // TODO number of done task for each type
    // TODO number of tasks of each type
    // TODO number of times used command
    // TODO number of tasks deleted
    // TODO number of done/not done tasks deleted
    // TODO past searches
    // TODO log updates after completing each execution

}
