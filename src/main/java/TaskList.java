import java.util.LinkedList;

class TaskList extends LinkedList<Task> {
    private static LinkedList<Task> taskList;

    public TaskList() {
        taskList = new LinkedList<>();
    }

    public TaskList(LinkedList<Task> taskList) {
        TaskList.taskList = taskList;
    }

    public static LinkedList<Task> getList() {
        return taskList;
    }

    public static void print() {
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Task t = taskList.get(i);
            System.out.println(index + "." + t);
        }


    }

    /*public Task get(int n) {
        return texts.get(n);
    }*/
   /* public Task getLast() {
        return texts.getLast();
    }*/
    /*public int getNumber() {
        return texts.size();
    }*/

    /*public Task remove(int i) {
        return texts.remove(i);
    }*/
}
