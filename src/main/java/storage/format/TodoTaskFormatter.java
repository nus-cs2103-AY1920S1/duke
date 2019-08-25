package com.leeyiyuan.storage.format;


import com.leeyiyuan.task.Task;
import com.leeyiyuan.task.TodoTask;
import java.util.regex.Pattern;

/** 
 * Represents a formatter for a TodoTask.
 */
public class TodoTaskFormatter extends TaskFormatter {

    /** 
     * {@inheritDoc} 
     */
    @Override
    public TodoTask parse(String text) throws TaskParseException {
        if (!Pattern.matches("^T \\| [01] \\| .+$", text)) {
            throw new TaskParseException("Bad TodoTask format.");
        }

        String[] data = text.split(" \\| ", 3);
        TodoTask task = new TodoTask();
        task.setIsDone(data[1].equals("1"));
        task.setTitle(data[2]);
        return task;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public String format(Task task) throws TaskFormatException {
        if (!(task instanceof TodoTask)) {
            throw new TaskFormatException("Invalid task.");
        }

        return String.format(
                "T | %d | %s", ((TodoTask) task).getIsDone() ? 1 : 0, ((TodoTask) task).getTitle());
    }
}
