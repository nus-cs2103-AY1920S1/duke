//package mypackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> list;
    private int noOfTasks;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.noOfTasks = list.size();
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
        this.noOfTasks = 0;
    }

    public int getNoOfTasks() {
        return noOfTasks;
    }

    public List<Task> getList() {
        return list;
    }

    public void add(Task task) {
        list.add(task);
        noOfTasks++;
    }

    public void delete( int no) {
        list.remove(no -1);
        noOfTasks--;
    }

    public void done(int no) {
        list.get(no - 1).markAsDone();
    }
}
