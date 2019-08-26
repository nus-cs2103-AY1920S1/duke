public interface ControllerInterface {
    void start();

    void stop();

    void addTask(String command);
    
    void doneTask(String command);

    void deleteTask(String command);

    void whatsGoingOn(String command);

    boolean isEndCommand(String command);

    void listTasks();

    void findTasks(String command);
}
