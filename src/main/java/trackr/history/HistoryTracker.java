package trackr.history;

import trackr.tasklist.TaskList;

import java.util.Stack;

public class HistoryTracker {

    Stack<String> inputHistory;
    Stack<TaskList> tasklistHistory;

    public HistoryTracker() {
        inputHistory = new Stack<>();
        tasklistHistory = new Stack<>();
    }

    public void addHistory(String userInput, TaskList tasks) {
        inputHistory.push(userInput);
        tasklistHistory.push(tasks);
    }

    public String retrievePreviousInput() {
        return inputHistory.peek();
    }

    public boolean isEmpty() {
        return inputHistory.isEmpty();
    }

    public TaskList retrieveHistory() {
        inputHistory.pop();
        return tasklistHistory.pop();
    }
}
