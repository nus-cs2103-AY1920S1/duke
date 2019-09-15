package duke.core;

import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;


public class TaskList {

    private ArrayList<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage){
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(ArrayList<Task> list, Storage storage){
        this.taskList = list;
        this.storage = storage;
    }

    public void addToList(Task task) throws IOException {
        this.taskList.add(task);
        this.storage.overwriteStorage(taskList);
    }

    public ArrayList<Task> getList(){
        return this.taskList;
    }

    public Task getTaskAt(int index){
        return this.taskList.get(index - 1);
    }


    public void removeFromList(Task task) throws IOException {
        boolean isRemoved = this.taskList.remove(task);
        if (isRemoved) {
            this.storage.overwriteStorage(taskList);
        }
    }

    public void setDoneInList(int index) throws IOException {
        this.taskList.get(index - 1).setDone();
        this.storage.overwriteStorage(taskList);
    }

    int getSize(){
        return this.taskList.size();
    }

    public int getTaskID(Task task) {
        return taskList.indexOf(task) + 1;
    }


}

