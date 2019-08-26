import java.util.*;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected ArrayList<String> fileList;

    public TaskList(ArrayList<String> fileList) throws DukeException{
        this.fileList = fileList;
        this.taskList = new ArrayList<Task>();

        for(int i=0; i<fileList.size(); i++) {
            String[] listItem = fileList.get(i).split(" \\| ");
            String type = listItem[0];

            if (type.equals("T")) {
                Task add = new Todo(listItem[2]);
                this.taskList.add(add);
            } else if(type.equals("D")) {
                Task addTask = new Deadline(listItem[2], listItem[3]);
                this.taskList.add(addTask);
            } else {
                Task add = new Events(listItem[2], listItem[3]);
                this.taskList.add(add);
            }
        }
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public String printTaskList() {
        String printable = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            printable += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        return printable;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task get(int num) {
        return this.taskList.get(num);
    }

    public void remove(int num) {
        this.taskList.remove(num);
    }

    public int size() {
        return this.taskList.size();
    }
}

