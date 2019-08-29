package cs2103t.duke.task;

import cs2103t.duke.exception.EmptyDescriptionException;
import cs2103t.duke.exception.IncorrectTaskFormatException;

import java.util.Scanner;

public class Event extends Task {
    //Deadlines can have (notes)
    private String description;
    private String notesInBrackets;
    private String datetime;

    private Event() {}
    private Event(String descr, boolean completed) throws IncorrectTaskFormatException {
        super.completed = completed;
        super.taskType = TaskType.E;

        setupDetails(descr);
    }
    private void setupDetails(String input) throws IncorrectTaskFormatException {
        String[] tmp = input.split("/");
        //inputs should only have <=1 '/' characters
        if (tmp.length < 2)
            throw new IncorrectTaskFormatException("at");

        this.description = tmp[0].trim();
        Scanner tmp2 = new Scanner(tmp[1]);
        String term = tmp2.next().trim();
        String datetime = "";
        if (tmp2.hasNext())
            datetime = tmp2.nextLine().trim();
        tmp2.close();

        if (datetime.equals(""))
            throw new IncorrectTaskFormatException("at");

        this.notesInBrackets = String.format("%s: %s", term, datetime);

        super.description = String.format("%s (%s)", this.description, this.notesInBrackets);
        this.datetime = datetime;
    }

    public static Event create(String descr) throws EmptyDescriptionException, IncorrectTaskFormatException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("an event");

        Event newTask = new Event(descr.trim(), false);
        return newTask;
    }

    @Override
    public String getDescription() {
        return String.format("%s | %s", this.description, this.datetime);
    }
}
