package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task. A <code>Task</code> object contains a description,
 * a  boolean representing whether or not the task has been done and additional information.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;
    protected String info;
    protected Date date;

    /**
     * Empty constructor for Task.
     */
    public Task() {

    }


    /**
     * Constructs a new Task.
     * @param description description of the task
     * @param isDone whether or not the task has been done
     */
    public Task(String description, boolean isDone, String info) {
        this.description = description.trim();
        this.isDone = isDone;
        this.info = info.trim();
    }

    /**
     * Returns a boolean representing whether or not the task is done.
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string which is the first character of each task type.
     * @return a "T", "D", or "E"
     */
    public String getSymbol() {
        switch (type) {
        case TODO:
            return "T";
        case DEADLINE:
            return "D";
        case EVENT:
            return "E";
        default:
            return "";
        }
    }

    /**
     * Returns a boolean representing whether or not the task is done.
     * @return true if the task is done, false otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✗"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overrides toString method.
     * @return a String in the to-be-displayed format
     */
    @Override
    public String toString() {
        if (info.equals("")) {
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description;
        } else {
            String[] infos = info.split(" ", 2);
            return "[" + getSymbol() + "][" + getStatusIcon() + "] "
                    + description + " (" + infos[0] + ":  " +  infos[1] + ")";
        }
    }

    /**
     * Returns a String in the format for file saving. An abstract method to be implemented in child classes.
     */
    public abstract String getFileStringFormat();

    /**
     * Checks if the string is in date format dd/MM/yyyy HHmm and returns the parsed form of the  date.
     * @param str the task's info String
     * @return true if the task is done, false otherwise
     */
    public String checkDate(String str)  {
        try {
            DateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
            DateFormat formatter = new SimpleDateFormat("d MMMM yyyy ha");
            date = parser.parse(str);
            String output = formatter.format(date);

            String[] arr = output.split(" ",  4);

            int day = Integer.parseInt(arr[0]);
            String finalString = "";
            finalString += arr[0];

            switch (day % 10) {
            case 1:
                if (day == 11) {
                    finalString += "th";
                } else {
                    finalString += "st";
                }
                break;
            case 2:
                if (day == 12) {
                    finalString += "th";
                } else {
                    finalString += "nd";
                }
                break;
            case 3:
                finalString += "rd";
                break;
            default:
                finalString += "th";
                break;

            }
            finalString += " of " + arr[1] + " " + arr[2] + ", " + arr[3];
            return finalString;
        } catch (ParseException e) {
            return str;
        }
    }
}