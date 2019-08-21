public class TodoTask extends Task {

    protected TodoTask(String description) {
        super(description);
    }

    @Override
    protected String getStatus() {
        return "[T]" + super.getStatus();
    }

}
