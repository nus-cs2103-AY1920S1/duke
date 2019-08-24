public class ToDo extends Task {

    ToDo(String description) throws EmptyDescriptionException {
        this(description, "0");
    }

    ToDo(String description, String isDone) throws EmptyDescriptionException {
        super("a todo", description, isDone);
    }

    @Override
    String getTypeCode() {
        return "T";
    }
}
