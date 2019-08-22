import java.util.ArrayList;
import java.util.List;

public class Storage {

    Formatter formatter = new Formatter();
    List<Task> taskList = new ArrayList<>();
    MessageGenerator msgGenerator = new MessageGenerator();

    public Storage() {
        //placeholder value in task
        taskList.add(null);
    }

    public int noTasks() {
        return taskList.size() - 1;
    }

    public void addTask(Task task) {
        taskList.add(task);
        msgGenerator.printAdd(task, noTasks());
    }

    public void removeTask(int taskNo) {
        msgGenerator.printRemove(taskList.get(taskNo), noTasks()-1);
        taskList.remove(taskNo);
    }

    public List<String> listify() {
        List<String> list = new ArrayList<String>();
        for (Task task: taskList) {
            if (task != null) {
                list.add(task.toString());
            }
        }
        return list;
    }

    public void printList() {
        msgGenerator.printList(listify());
    }

    public boolean invalidTaskNo(int taskNo) {
        return taskNo >= taskList.size();
    }

    public void setDone(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            taskList.get(taskNo).setDone();
            msgGenerator.printDone(taskList.get(taskNo));
        } catch (DukeException e) {
            e.printError();
        }
    }

    public void deleteTask(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            removeTask(taskNo);
        } catch (DukeException e) {
            e.printError();
        }
    }

}
