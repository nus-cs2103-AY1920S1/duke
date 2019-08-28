package tasks;

import utils.StringToDate;

public class Event extends Task {
    private StringToDate time;

    public Event(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String printForStorage() {
        String borderAndSpace = " | ";
        String str = super.printForStorage();
        str += "E" + borderAndSpace;
        if (this.isDone) {
            str += "1" + borderAndSpace;
        } else {
            str += "0" + borderAndSpace;
        }
        str += this.name + borderAndSpace + this.time;
        return str;
    } 

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.toString() + ")";
    }
}