public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String saveString() {
        String saveString = "E | ";

        if (this.isCompleted()) {
            saveString += 1;
        } else {
            saveString += 0;
        }

        saveString += " | ";
        saveString += this.getDescription();
        saveString += " | ";
        saveString += this.getTime();
        saveString += "\n";

        return saveString;
    }

    @Override
    public String toString() {
        return "[E][" + this.getMark() + "] " + this.getDescription() + " (at: " + time + ")\n";
    }
}
