import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> lst;

    public TaskList(LinkedList<String> list) {
        lst = new LinkedList<>();
        for (String s : list) {
            String[] arr = s.split("/");
            String task = arr[0];
            int done = Integer.parseInt(arr[1]);
            String desc = arr[2];
            switch (task) {
                case "D":
                    Deadline deadline = Deadline.of(desc, arr[3]);
                    if (done == 1) {
                        deadline.markAsDone();
                    }
                    lst.addLast(deadline);
                    break;
                case "T":
                    Todo todo = Todo.of(desc);
                    if (done == 1) {
                        todo.markAsDone();
                    }
                    lst.addLast(todo);
                    break;
                case "E":
                    Event event = Event.of(desc, arr[3]);
                    if (done == 1) {
                        event.markAsDone();
                    }
                    lst.addLast(event);
                    break;
            }
        }
    }

    public TaskList() {
        lst = new LinkedList<>();
    }

    public int getNumTasks() {
        return lst.size();
    }

    public void addTask(Task task) {

    }

    public Task deleteTask(int index) {
        return lst.remove(index);
    }

    public LinkedList<String> tasksToStringList(boolean isSaveFormat) {
        LinkedList<String> stringLst = new LinkedList<>();
        int i = 0;
        for (Task task : lst) {
            if (isSaveFormat) {
                stringLst.addLast(task.toSaveFormat());
            } else {
                stringLst.addLast(task.toSaveFormat());
                stringLst.addLast(String.format("%d.%s", i, task));
                ++i;
            }
        }
        return stringLst;
    }
}
