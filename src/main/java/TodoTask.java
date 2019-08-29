class TodoTask extends Task {

    TodoTask(String description) {
        super(description);
    }

    TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    String getType() {
        return "T";
    }

    @Override
    String getStatus() {
        return "[T]" + super.getStatus();
    }

}
