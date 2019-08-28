//package mypackage;

public class Deadline extends Task {

    protected Date date;


    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = new String[2];
        dateAndTime = by.split(" ");
        String[] date = new String[3];
        date = dateAndTime[0].split("/");
        this.date = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                            Integer.parseInt(date[2]), Integer.parseInt(dateAndTime[1]));
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}