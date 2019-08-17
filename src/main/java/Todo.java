public class Todo extends Task {
    Todo(String desc) {
        super(desc.trim());
    }

    @Override
    String getInitial() {
        return "T";
    }

    @Override
    String getAdditionalMessage() {
        return "";
    }
}
