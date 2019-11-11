package seedu.duke.tasks;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String icon;

    TaskType(String icon) {
        this.icon = icon;
    }

    String getTaskIcon() {
        return icon;
    }
}
