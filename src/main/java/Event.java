public class Event extends Task {
    Date time;

    public Event(String description, String time) {
        super(description);
        String[] dateAndTime = time.split(" ");
        String[] date = dateAndTime[0].split("/");
        this.time = new Date(date[0], date[1], date[2], dateAndTime[1]);
    }

    public String getTime() {
        return time.toString();
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
