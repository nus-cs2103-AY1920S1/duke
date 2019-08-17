public class ToDo extends Task {
    public ToDo(int index, String topic) {
        super(index, topic);
        this.type = "T";
        this.details = topic;
    }
}
