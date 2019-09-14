package duke.task;

enum TaskPriority {
    EMERGENCY(1),
    URGENT(2),
    STANDARD(3),
    NORMAL(4);

    private final int value;

    TaskPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
