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
    private String category;
    private Integer tasknum;
    private String noteDescription;
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\w+)"
            + "\\s*(?<completionStatus>(\\[[01]\\])?)"
            + "\\s*(?<description>([\\w\\s\\d{}.|]+)?)"
            + "(?:(/by|/at))?(?<date>([\\w\\s\\d/]+)?)");
    public static final Pattern NOTE_FORMAT = Pattern.compile("\\{(?<task>[0-9.]+)\\}"
            + "\\s*(?<category>([\\w\\s\\d]+)?)"
            + "\\|?\\s*(?<description>([\\w\\s\\d]+)?)");

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
                executeCommand(command);
            }
        } else {
            ui.printErrorMsg2();
            isSafe = false;
        }
    }

    /**
     * Parses the commands for handling notes.
     * @param noteCommand actual command for handling notes
     */
    public void splitNotesCommand(String noteCommand) {
        Matcher matcher = NOTE_FORMAT.matcher(noteCommand);
        if (matcher.find()) {
            tasknum = Integer.parseInt(matcher.group("task")) - 1;
            category = matcher.group("category");
            noteDescription = matcher.group("description");
        } else {
            ui.printErrorMsg2();
            isSafe = false;
        }
    }

    public boolean isSafe() {
        return isSafe;
    }

    /**
     * Method to initialize the various variables with the parsed command.
     * @param matcher contains the parsed command according to the above regex format
     */
    public void splitTaskKeywords(Matcher matcher) {
        command = matcher.group("commandWord");
        isDone = matcher.group("completionStatus").equals("[1]");
        description = matcher.group("description").trim();
        if (!matcher.group("date").isEmpty()) {
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                date = LocalDateTime.parse(matcher.group("date").trim(), inputFormat);
            } catch (DateTimeParseException e) {
                ui.printWrongDate();
                isSafe = false;
            }
        }
    }

    /**
     * Method that executes the commands according to the appropriate command word.
     * @param command contains the main command word
     */
    public void executeCommand(String command) {
        switch (command) {
        case "todo":
        case "deadline":
        case "event":
        case "notebook":
            scheduler.addTask(command, description, isDone, date);
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
        case "addnote":
            splitNotesCommand(description);
            scheduler.getTasks().get(tasknum).addNote(category, noteDescription, date);
            break;
        case "deletenote":
            splitNotesCommand(description);
            scheduler.getTasks().get(tasknum).removeNote(category);
            break;
        default:
            ui.printErrorMsg1();
            isSafe = false;
        }
    }

}


