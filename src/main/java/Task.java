public interface Task {
    static final char UNICODE_TICK = (char) 0x2713;
    static final char UNICODE_CROSS = (char) 0x2718;
    
    static char getStatusIcon(boolean isDone) {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }

    public Task getTaskMarkedAsDone();
    public Task getTaskMarkedUndone();
}
