package duke.logic;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> arr;

    public TaskList() {
        this.arr = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.arr = taskList;
    }

    public void add(Task t) {
        arr.add(t);
    }

    public void delete(int num) {
        arr.remove(num);
    }

    public ArrayList<Task> getArr() {
        return this.arr;
    }

    public Task getLast() {
        return this.arr.get(arr.size()-1);
    }

    public int getSize() {
       return this.arr.size();
    }

    public void markAsDone(int index) {
        this.arr.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return this.arr.get(index);
    }

    public ArrayList<Task> getMatchingWordsList(String findText) {

        ArrayList<Task> matchingWordsList = new ArrayList<>();
        String[] stringArr = findText.split(" ");
        for (Task t : arr) {
            for (String str: stringArr) {
                if (t.getTaskName().contains(str)) {
                    matchingWordsList.add(t);
                    break;
                }
            }
        }
        return matchingWordsList;
    }



}
