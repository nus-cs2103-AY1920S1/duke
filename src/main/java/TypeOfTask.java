public enum TypeOfTask {
    TODO, DEADLINE, EVENT;

    @Override
    public String toString() {
        String taskType = super.toString();
        return taskType.substring(0, 1) + taskType.substring(1).toLowerCase();
    }
}
