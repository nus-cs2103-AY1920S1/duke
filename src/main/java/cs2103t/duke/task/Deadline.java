package cs2103t.duke.task;

import cs2103t.duke.exception.EmptyDescriptionException;
import cs2103t.duke.exception.IncorrectTaskFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    //Deadlines can have (notes)
    private String description;
    private String notesInBrackets;
    private String datetime;
    private Date date;

    private Deadline() {}
    private Deadline(String descr, boolean completed) throws IncorrectTaskFormatException {
        super.completed = completed;
        super.taskType = TaskType.D;

        setupDetails(descr);
    }
    private void setupDetails(String input) throws IncorrectTaskFormatException {
        String[] tmp = input.split("\\s+/by\\s+");
        //inputs should only have <=1 '/' characters
        this.description = tmp[0];

        if (tmp.length < 2) {
            throw new IncorrectTaskFormatException("by");
        }

        String term = "by";
        String date = tmp[1];

        if (date.equals(""))
            throw new IncorrectTaskFormatException("by");
        this.notesInBrackets = String.format("%s: %s", term, date);

        super.description = String.format("%s (%s)", this.description, this.notesInBrackets);

        this.datetime = date;
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Deadline create(String descr) throws EmptyDescriptionException, IncorrectTaskFormatException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("a deadline");

        Deadline newTask = new Deadline(descr.trim(), false);
        return newTask;
    }

    @Override
    public String getDescription() {
        return String.format("%s | %s", this.description, this.datetime);
    }
}
