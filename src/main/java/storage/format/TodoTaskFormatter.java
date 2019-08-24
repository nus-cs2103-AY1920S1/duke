package com.leeyiyuan.storage.format;

import java.util.regex.Pattern;

import com.leeyiyuan.exception.DukeIllegalArgumentException;
import com.leeyiyuan.storage.format.TaskFormatException;
import com.leeyiyuan.storage.format.TaskFormatter;
import com.leeyiyuan.storage.format.TaskParseException;
import com.leeyiyuan.task.TodoTask;
import com.leeyiyuan.task.Task;

public class TodoTaskFormatter extends TaskFormatter {

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

    @Override
    public String format(Task task) throws TaskFormatException {
        if (!(task instanceof TodoTask)) {
            throw new TaskFormatException("Invalid task.");
        }

        return String.format("T | %d | %s",
                ((TodoTask)task).getIsDone() ? 1 : 0,
                ((TodoTask)task).getTitle());
    }

}
