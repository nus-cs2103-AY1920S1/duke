public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean status) {
        super(description);
        this.isDone = status;
    }

    @Override
    public String toFileFormat() {
        StringBuilder fileFormat = new StringBuilder();

        fileFormat.append("T~");

        if (this.isDone) {
            fileFormat.append("1~");
        } else {
            fileFormat.append("0~");
        }

        fileFormat.append(this.description);

        return fileFormat.toString();
    }

    @Override
    public String toString() {
        String task = "[T][" + this.getStatusIcon() +  "] " + description;
        return task;
    }
}