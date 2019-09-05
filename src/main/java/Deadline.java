public class Deadline extends Task {
    String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String saveString() {
        String saveString = "D | ";

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
        return "[D][" + this.getMark() + "] " + this.getDescription() + " (by: " + time + ")\n";
    }
}
