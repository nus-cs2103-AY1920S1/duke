package tasks;

import utils.StringToDate;

public class Deadline extends Task {
    private StringToDate time;

    public Deadline(String name, StringToDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String printForStorage() {
        String borderAndSpace = " | ";
        String str = super.printForStorage();
        str += "D" + borderAndSpace;
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
        return "[D]" + super.toString() + " (by: " + this.time.toString() + ")";
    }
}