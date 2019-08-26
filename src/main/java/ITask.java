public interface ITask {
    String getName();
    void markDone();
    String getTaskType();
    boolean isDone();
    @Override
    String toString();

}

