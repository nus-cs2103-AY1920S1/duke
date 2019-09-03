package parser;

import tasklist.TaskList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalTime.parse;

public class parser {
    private boolean isDone;
    private String description;
    private LocalDateTime date;
    private boolean isSafe = true;

    public parser(){};

    public void parse(String fullCommand, TaskList scheduler) {
        Pattern command_format = Pattern.compile("(?<commandWord>\\w+)"
                + "\\s*(?<completionStatus>(\\[[01]\\])?)"
                + "\\s*(?<description>([\\w\\s\\d]+)?)"
                + "(?:(/by|/at))?(?<date>([\\w\\s\\d/]+)?)");
        Matcher matcher = command_format.matcher(fullCommand);
        if (!matcher.find()) {
            System.out.println("    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "    ____________________________________________________________");
            isSafe = false;
        } else {
            isDone = matcher.group("completionStatus").equals("[1]");
            description = matcher.group("description").trim();
            if (!matcher.group("date").isEmpty()) {
                // Parsing the date
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                date = LocalDateTime.parse(matcher.group("date").trim(), inputFormat);
            }
            switch (matcher.group("commandWord")) {
            case "todo":
            case "deadline":
            case "event":
                scheduler.addTask(description, isDone, date);
                break;
            case "list":
                scheduler.listTasks();
                break;
            case "done":
                scheduler.completeTask(description);
                break;
            case "delete":
                scheduler.removeTask(description);
                break;
            default:
                System.out.println("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know that command :-(\n" +
                        "    ____________________________________________________________");
                isSafe = false;
            }
        }
    }



    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isSafe() {
        return isSafe;
    }
}


