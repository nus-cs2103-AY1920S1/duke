public class Deadline extends Task {
    String type;
    String date;

    public Deadline(String task_name, String date) {
        super(task_name);
        type = "deadline";
        this.date = date;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getDate() {
        return date;
    }
}
