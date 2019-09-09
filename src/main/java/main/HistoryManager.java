package main;

import task.TaskList;
import java.util.Stack;

public class HistoryManager {
    Storage storage;
    Stack<TaskList> records;
    public HistoryManager(Storage storage) {
        this.storage = storage;
        this.records = new Stack<TaskList>();
    }

    public void updateRecords() {
        TaskList tasks = new TaskList(storage.load());
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
}
