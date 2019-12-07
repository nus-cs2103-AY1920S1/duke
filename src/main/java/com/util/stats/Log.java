package com.util.stats;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Contains information about a command that was executed.
 */
public class Log {

    private LocalDateTime dateTimeStamp;
    private String commandWord, task;
    private ArrayList<String> keywords;

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * Constructor when adding new date to file.
     * @param commandWord
     * @param task
     * @param keywords
     */
    public Log(String commandWord, String task, ArrayList<String> keywords) {
        dateTimeStamp = LocalDateTime.now();
        this.commandWord = commandWord;
        this.task = task;
        this.keywords = keywords;
    }

    /**
     * Constructor used when reading data from file.
     * @param date Date of execution of command
     * @param time Time of execution of command
     * @param commandWord
     * @param task If command modified the list of tasks, the task that was involved
     * @param keywords If command execute was 'find', list of keywords searched
     */
    public Log(String date, String time, String commandWord, String task, ArrayList<String> keywords) {
        String dateTime = date + " " + time;
        this.dateTimeStamp = LocalDateTime.parse(dateTime, dateTimeFormat);
        this.commandWord = commandWord;
        this.task = task;
        this.keywords = keywords;
    }

    public String getDate() {
        return dateFormat.format(dateTimeStamp);
    }

    public String getTime() {
        return timeFormat.format(dateTimeStamp);
    }

    public LocalDateTime getDateTimeStamp() {
        return dateTimeStamp;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getTask() {
        if (task == null) {
            return "NA";
        }
        return task;
    }

    public String getKeywords() {
        if (keywords == null) {
            return "NA";
        }
        return keywords.toString();
    }

}
