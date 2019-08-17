interface TaskModelInterface {
    void initialize();
    void registerObserver(TaskObserver o);
    void removeObserver(TaskObserver o);
}
