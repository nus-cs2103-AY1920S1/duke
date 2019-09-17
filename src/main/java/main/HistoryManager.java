/*package main;

import task.TaskList;
import java.util.Stack;

public class HistoryManager {
    String filepath;
    Stack<TaskList> records;
    public HistoryManager(String filepath) {
        this.filepath = filepath;
        this.records = new Stack<TaskList>();
    }

    public void updateRecords() {
        Storage storage = new Storage(filepath);
        TaskList tasks = new TaskList(storage.load());
        System.out.println("Mark: \n" + tasks.toString());
        records.push(tasks);
    }

    public TaskList undo() {
        if (records.isEmpty()) {
            return new TaskList();
        }
        records.pop();
        if (records.isEmpty()) {
            return new TaskList();
        }
        TaskList prev = records.peek();
        return prev;
    }
}*/
