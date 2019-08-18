public interface ControllerInterface {
    void start();
    void stop();
    void addTask(String command);
    void doneTask(String command);
    void listTasks();
}
