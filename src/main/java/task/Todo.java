package task;

/**
 * <p>
 *     A standard Task with a name and no additional info.
 * </p>
 */
public class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    public String getAdditionalInfo() {
        return "";
    }

    @Override
    protected String getTypeSymbol() {
        return "[T]";
    }

    @Override
    protected String displayAdditionalInfo() {
        return "";
    }
}
