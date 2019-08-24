package com.leeyiyuan.storage.format;


import com.leeyiyuan.task.EventTask;
import com.leeyiyuan.task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EventTaskFormatter extends TaskFormatter {

    @Override
    public EventTask parse(String text) throws TaskParseException {
        if (!Pattern.matches("^E \\| [01] \\| .+ \\| .+$", text)) {
            throw new TaskParseException("Bad EventTask format.");
        }

        String[] data = text.split(" \\| ", 4);
        EventTask task = new EventTask();
        task.setIsDone(data[1].equals("1"));
        task.setTitle(data[2]);
        task.setAt(LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        return task;
    }

    @Override
    public String format(Task task) throws TaskFormatException {
        if (!(task instanceof EventTask)) {
            throw new TaskFormatException("Invalid task.");
        }

        return String.format(
                "E | %d | %s | %s",
                ((EventTask) task).getIsDone() ? 1 : 0,
                ((EventTask) task).getTitle(),
                ((EventTask) task).getAt().format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }
}
