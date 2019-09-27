package trackr.history;

import trackr.tasklist.TaskList;

import java.util.Stack;

/**
 * Keeps track of input and tasklist history of the current session.
 * Supports methods that allow adding and retrieval of history of the program.
 */
public class HistoryTracker {

    private Stack<String> inputHistory;
    private Stack<TaskList> tasklistHistory;

    public HistoryTracker() {
        inputHistory = new Stack<>();
        tasklistHistory = new Stack<>();
    }

    /**
     * Records user input and task list state.
     * @param userInput User input
     * @param tasks List of tasks
     */
    public void addHistory(String userInput, TaskList tasks) {
        inputHistory.push(userInput);
        tasklistHistory.push(tasks);
    }

    /**
     * Returns the previous user input.
     * @return String Previous user input
     */
    public String retrievePreviousInput() {
        return inputHistory.peek();
    }

    /**
     * Checks if input history list is empty.
     * @return boolean True if input history list is empty, false otherwise
     */
    public boolean isEmpty() {
        return inputHistory.isEmpty();
    }

    /**
     * Retrieve previous task list before previous command was executed.
     * @return TaskList List of tasks before previous command was executed
     */
    public TaskList retrieveHistory() {
        inputHistory.pop();
        return tasklistHistory.pop();
    }

    public Stack<String> getInputHistory() {
        return inputHistory;
    }
}
