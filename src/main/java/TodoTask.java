class TodoTask extends Task {

    TodoTask(String description) {
        super(description);
    }

    @Override
    String getStatus() {
        return "[T]" + super.getStatus();
    }

}
