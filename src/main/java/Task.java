import java.util.LinkedList;
import java.util.ListIterator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static LinkedList<Task> taskList = new LinkedList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void doTask() {
        this.isDone = true;
    }

    public static String formatToFile() {
        ListIterator<Task> iter = taskList.listIterator();
        StringBuilder taskListFileFormat = new StringBuilder();

        while (iter.hasNext()) {
            Task current = iter.next();
            taskListFileFormat.append(current.toFileFormat());

            if (iter.hasNext()) {
                taskListFileFormat.append("\n");
            }
        }

        return taskListFileFormat.toString();
    }

    protected LocalDateTime storeAsDateTime(String date) {
        StringBuilder sb = new StringBuilder();
        String[] temp = date.split(" ");


        int timeInt = Integer.parseInt(temp[2]);
        String hour = (timeInt / 100) + "";
        String minutes = (timeInt % 100) + "";

        String dateString = temp[1];
        String[] dateStringArr = dateString.split("/");

        //If day of the week is single digit pad with 0
        if (dateStringArr[0].length() == 1) {
            dateStringArr[0] = "0" + dateStringArr[0];
        }

        //If month is single digit pad with 0
        if (dateStringArr[1].length() == 1) {
            dateStringArr[1] = "0" + dateStringArr[1];
        }

        //Formatting for LocalDateTime to parse
        sb.append(dateStringArr[2]);
        sb.append("-");
        sb.append(dateStringArr[1]);
        sb.append("-");
        sb.append(dateStringArr[0]);
        sb.append("T");

        if (hour.length() == 1) {
            hour = "0" + hour;
        }

        if (minutes.length() == 1) {
            minutes = "0" + minutes;
        }

        sb.append(hour + ":" + minutes + ":00");

        LocalDateTime dateTime = LocalDateTime.parse(sb.toString());

        return dateTime;
    }

    public String toFileFormat() {
        return "";
    }

    @Override
    public String toString() {
        String task = "[" + this.getStatusIcon() + "] " + description;
        return task;
    }

}