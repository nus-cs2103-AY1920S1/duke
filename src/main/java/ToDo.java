public class ToDo extends Task {
    public ToDo(String topic) {
        super(topic);
        this.type = "T";
        this.details = topic;
    }
}
