import java.util.ArrayList;

public class TaskList {
    /*
    1. Creating an arraylist when tasklist is constructed
    2. ArrayList<Task> getList()
    3. addTask(Task task)
    4. delTask(int index)
    5. markAsDone(int index)
     */
    private ArrayList<Task> list;
    private String listString;
    private static String INDENT = "    ";

    public TaskList() {
        this.list = new ArrayList<>();
        this.listString = "";
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String getListString() {
        return listString;
    }

    public void addTask(Task newTask) {
        list.add(newTask);
        updateTodoString();
    }

    public void deleteTask(int index) {
        list.remove(index);
        updateTodoString();
    }

    public void markTaskAsDone(int index) {
        list.get(index).markAsDone();
        updateTodoString();
    }

    public void updateTodoString() {
        listString = "" + INDENT + " ";
        for (int i = 0; i < list.size(); i++) {
            listString += (i + 1) + ". " + list.get(i);
            if (i != (list.size() - 1)) {
                listString += '\n' + INDENT + ' ';
            }
        }
    }
}
