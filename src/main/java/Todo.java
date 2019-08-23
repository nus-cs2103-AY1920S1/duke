public class Todo extends Task {
    public static final String INITIAL = "T";

    Todo(String desc) {
        super(desc.trim());
    }

    @Override
    String getInitial() {
        return INITIAL;
    }
}
