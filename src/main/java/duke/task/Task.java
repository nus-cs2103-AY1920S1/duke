package duke.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;
    protected String info;
    protected Date date;

    public Task(String description, boolean isDone, String info) {
        this.description = description.trim();
        this.isDone = isDone;
        this.info = info.trim();
    }

    public boolean isDone() {
        return isDone;
    }

    public String getSymbol() {
        switch (type){
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

    public Type getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return  description;
    }

    public Date getDate() {
        return date;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (info.equals("")) {
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description;
        } else {
            String[] infos = info.split(" ", 2);
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description + " (" + infos[0] +":  " +  infos[1] + ")";
        }
    }

    public abstract String getFileStringFormat();

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