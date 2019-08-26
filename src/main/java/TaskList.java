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

            for(int j=0; j<listItem.length; j++) {
                System.out.println(listItem[j]);
            }


            if (type.equals("T")) {
                System.out.println("at todo");
                Task add = new Todo(listItem[2]);
                System.out.println("before add at T");
                this.taskList.add(add);
                System.out.println("added at T");
            } else if(type.equals("D")) {
                System.out.println("at deadline");
                Task addTask = new Deadline(listItem[2], listItem[3]);
                System.out.println("before add at D");
                this.taskList.add(addTask);
                System.out.println("added at D");
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
            printable += (i + 1) + "." + taskList.get(i).toString();
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

