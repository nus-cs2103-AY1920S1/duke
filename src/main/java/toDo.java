public class toDo extends Task {

    public toDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }

}
