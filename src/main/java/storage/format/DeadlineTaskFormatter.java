package com.leeyiyuan.storage.format;


import com.leeyiyuan.task.DeadlineTask;
import com.leeyiyuan.task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class DeadlineTaskFormatter extends TaskFormatter {

    @Override
    public DeadlineTask parse(String text) throws TaskParseException {
        if (!Pattern.matches("^D \\| [01] \\| .+ \\| .+$", text)) {
            throw new TaskParseException("Bad DeadlineTask format.");
        }

        String[] data = text.split(" \\| ", 4);
        DeadlineTask task = new DeadlineTask();
        task.setIsDone(data[1].equals("1"));
        task.setTitle(data[2]);
        task.setBy(LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        return task;
    }

    @Override
    public String format(Task task) throws TaskFormatException {
        if (!(task instanceof DeadlineTask)) {
            throw new TaskFormatException("Invalid task.");
        }

        return String.format(
                "D | %d | %s | %s",
                ((DeadlineTask) task).getIsDone() ? 1 : 0,
                ((DeadlineTask) task).getTitle(),
                ((DeadlineTask) task).getBy().format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
