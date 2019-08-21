import java.util.ArrayList;
import java.util.List;

public class Storage {

    Formatter formatter = new Formatter();
    List<Task> taskList = new ArrayList<>();

    public Storage() {
        taskList.add(null);
    }

    public void addTask(Task task) {
        taskList.add(task);
        formatter.printLine();
        System.out.println(task.addTask() + task);
        formatter.printFormat("Now you have " + (taskList.size()-1) + " task(s) in the list.");
        formatter.printLine();
    }

    public void printList() {
        formatter.printLine();
        formatter.printFormat("Here are the tasks in your list:");
        for (Task task : taskList) {
            if (task != null) {
                System.out.println(formatter.format(task.listify() + task));
            }
        }
        formatter.printLine();
    }

    public void setDone(int taskNo) {
        taskList.get(taskNo).setDone();
        formatter.printLine();
        System.out.println(taskList.get(taskNo).doneify());
        formatter.printLine();
    }

}
