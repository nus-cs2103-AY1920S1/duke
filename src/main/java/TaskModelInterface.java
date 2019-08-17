interface TaskModelInterface {
    void initialize();
    void registerObserver(TaskObserver o);
    void removeObserver(TaskObserver o);
    int getTotalTasks();
    void addTask(TaskInterface task);
}
