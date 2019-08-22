import java.util.ArrayList;

class ToDoList {
    private ArrayList<Task> taskList;
    ToDoList() {
        taskList = new ArrayList<>(100);
    }
    void addTask(String task) {
        taskList.add(new Task(task));
        System.out.println("added: " + task);
    }
    void listAllTasks() {
        int total = taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < total; i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
    void checkTask(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.taskDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }
}
