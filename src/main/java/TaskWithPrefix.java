public class TaskWithPrefix extends Task {
    public TaskWithPrefix(String description) {
        super(description.replaceFirst("^.*?\\s",""));
    }

}
