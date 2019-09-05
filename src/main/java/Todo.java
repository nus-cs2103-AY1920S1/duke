public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveString() {
        String saveString = "T | ";

        if (this.isCompleted()) {
            saveString += 1;
        } else {
            saveString += 0;
        }
        saveString += " | ";
        saveString += this.getDescription();
        saveString += "\n";

        return saveString;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
