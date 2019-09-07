package duke.task;

enum TaskPriority {
    EMERGENCY(1),
    URGENT(2),
    STANDARD(3),
    NORMAL(4);

    private final int value;

    private TaskPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
