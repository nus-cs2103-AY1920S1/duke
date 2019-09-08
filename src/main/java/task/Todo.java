package task;

/**
 * A standard Task with a name and no additional info.
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
    protected String getAdditionalInfoForDisplay() {
        return "";
    }

    @Override
    public String getStorageStringFormat() {
        return "T" + " | "
                + getStatus() + " | "
                + getName();
    }
}
