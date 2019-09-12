package parser;

import ui.TextUi;
import tasklist.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser class to split up the user input in to separate variables. it then checks the command and executes as directed
 * It also splits up the input from save files and adds it in the same way to a TaskList object
 */

public class Parser {
    String command;
    private boolean isDone;
    String description;
    private LocalDateTime date;
    private boolean isSafe = true;
    private TaskList scheduler;
    private TextUi ui;
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\w+)"
            + "\\s*(?<completionStatus>(\\[[01]\\])?)"
            + "\\s*(?<description>([\\w\\s\\d]+)?)"
            + "(?:(/by|/at))?(?<date>([\\w\\s\\d/]+)?)");

    public Parser() {
        ui = new TextUi();
    }

    /**
     * method used to split up the user input and execute the required task.
     * @param fullCommand user input
     * @param scheduler TaskList object that commands are to be executed on
     * @param isLoading boolean variable to check if the input is from a user or save file (to stop some print actions)
     */
    public void parseTasks(String fullCommand, TaskList scheduler, boolean isLoading) {
        this.scheduler = scheduler;
        Matcher matcher = COMMAND_FORMAT.matcher(fullCommand);
        if (matcher.find()) {
            splitTaskKeywords(matcher);
            if (isSafe) {
                executeCommand(command,isLoading);
            }
        } else {
            ui.printErrorMsg2();
            isSafe = false;
        }
    }

    public void parseFina

    public boolean isSafe() {
        return isSafe;
    }

    public void splitTaskKeywords(Matcher matcher){
        command = matcher.group("commandWord");
        isDone = matcher.group("completionStatus").equals("[1]");
        description = matcher.group("description").trim();
        if (!matcher.group("date").isEmpty()) {
            try {
                // Parsing the date
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                date = LocalDateTime.parse(matcher.group("date").trim(), inputFormat);
            } catch (DateTimeParseException e) {
                ui.printWrongDate();
                isSafe = false;
            }
        }
    }

    public void executeCommand(String command, Boolean isLoading){
        switch (command) {
        case "todo":
        case "deadline":
        case "event":
            scheduler.addTask(command, description, isDone, date);
            if (!isLoading) {
                scheduler.printNewTask();
            }
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
        case "find":
            scheduler.findTasks(description);
            break;
        default:
            ui.printErrorMsg1();
            isSafe = false;
        }
    }

}


