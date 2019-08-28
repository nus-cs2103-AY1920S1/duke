package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public TaskList() {
        this.taskArray = new ArrayList<Task>();
    }

    public int getSize(){
        return taskArray.size();
    }

    public ArrayList<Task> getTaskList(){
        return this.taskArray;
    }

    public Task getTask(int num){
        return taskArray.get(num - 1);
    }

    public void addToRecord(Task t) {
        this.taskArray.add(t);
    }

    public Task removeTask(int num){
        return this.taskArray.remove(num - 1);
    }

    public ArrayList<Task> findTask(String inputString) {
        ArrayList<Task> returnedTaskList = new ArrayList<Task>();
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            String[] tSplit = t.getDescription().split(" ");
            for (int j = 0; j < tSplit.length; j++) {
                if(tSplit[j].equalsIgnoreCase(inputString)) {
                    returnedTaskList.add(t);
                }
            }
        }
        return returnedTaskList;
    }
}
