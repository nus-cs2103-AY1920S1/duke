package com.util.stats;

import com.commands.Command;
import com.tasks.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Keeps track of a log of executed tasks.
 * Contains methods which return summary statistics of logs.
 */
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
        //logger.log(Level.INFO, "Number of logs now: " + executionLog.size());
        statsStorage.save(executionLogs);
    }

    /**
     * For commands that did not need any descriptions.
     *
     * @param command
     */
    public void addLog(Command command) {
        addLog(command, null, null);
    }

    /**
     * For commands that modified the list of tasks.
     *
     * @param command
     * @param task    Task that was involved
     */
    public void addLog(Command command, Task task) {
        addLog(command, task, null);
    }

    /**
     * For command 'find'.
     *
     * @param command
     * @param keywords List of keywords that was searched
     */
    public void addLog(Command command, ArrayList<String> keywords) {
        addLog(command, null, keywords);
    }

    public int getNumTasksDone() {
        return filterLogsByCommand(executionLogs, "done").size();
    }

    public ArrayList<String> getListTasksDone() {
        ArrayList<Log> doneTasks = filterLogsByCommand(executionLogs, "done");
        ArrayList<String> output = doneTasks
                .stream()
                .map((l) -> l.getTask())
                .collect(Collectors.toCollection(ArrayList::new));
        return output;
    }
    public int getNumTasksDonePastWeek() {
        return filterLogsByCommand(getPastWeekLogs(executionLogs), "done").size();
    }

    private int getNumTaskTypeDone(String taskType) {
        String pattern = "^" + taskType;
        ArrayList<Log> toDoLogs = filterLogsByCommand(executionLogs, "done")
                .stream()
                .filter((l) -> l.getTask().matches(pattern))
                .collect(Collectors.toCollection(ArrayList::new));
        return toDoLogs.size();
    }

    public int getNumToDoDone() {
        return getNumTaskTypeDone("[T]");
    }

    public int getNumDeadlineDone() {
        return getNumTaskTypeDone("[D]");
    }

    public int getNumEventDone() {
        return getNumTaskTypeDone("[E]");
    }

    public int getNumTasksDeleted() {
        return filterLogsByCommand(executionLogs, "delete").size();
    }

    public ArrayList<String> getListTasksDeleted() {
        ArrayList<Log> deleteTasks = filterLogsByCommand(executionLogs, "delete");
        ArrayList<String> output = deleteTasks
                .stream()
                .map((l) -> l.getTask())
                .collect(Collectors.toCollection(ArrayList::new));
        return output;
    }

    public int getNumUndoneTasksDeleted() {
        ArrayList<Log> undoneDeleteTasks = filterLogsByCommand(executionLogs, "delete")
                .stream()
                .filter((l) -> l.getTask().matches("[x]"))
                .collect(Collectors.toCollection(ArrayList::new));
        return undoneDeleteTasks.size();
    }

    private ArrayList<String> getSearchedKeywords(ArrayList<Log> logArr) {
        ArrayList<Log> findLogs = filterLogsByCommand(executionLogs, "find");
        ArrayList <String> allKeywords = findLogs
                .stream()
                .map((l) -> l.getKeywords())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        return allKeywords;
    }

    public String getSearchedKeywords() {
        return getSearchedKeywords(executionLogs).toString();
    }

    public ArrayList<String> getSearchedKeywordsPastWeek() {
        return getSearchedKeywords(getPastWeekLogs(executionLogs));
    }

    private ArrayList<Log> getPastWeekLogs(ArrayList<Log> logArr) {
        LocalDateTime pastWeekStartDate = LocalDateTime.now().minusDays(7);
        ArrayList<Log> output = logArr
                .stream()
                .filter((l) -> l.getDateTimeStamp().isAfter(pastWeekStartDate))
                .collect(Collectors.toCollection(ArrayList::new));
        return output;
    }

    private ArrayList<Log> filterLogsByCommand(ArrayList<Log> logArr, String commandWord) {
        ArrayList<Log> output = logArr
                .stream()
                .filter((l) -> l.getCommandWord().equals(commandWord))
                .collect(Collectors.toCollection(ArrayList::new));
        return output;
    }

    private ArrayList<Log> getDoneLogs(ArrayList<Log> logArr) {
        return filterLogsByCommand(logArr, "done");
    }

    private ArrayList<Log> getDeletedLogs(ArrayList<Log> logArr) {
        return filterLogsByCommand(logArr, "delete");
    }

}
