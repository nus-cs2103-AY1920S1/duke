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
    private String command;
    private Integer taskindex;
    private String description;
    private LocalDateTime date;
    private boolean isSafe = true;
    private TaskList scheduler;
    private TextUi ui;
    private String category;
    private Integer noteIndex;
    private String noteDescription;
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\w+)"
            + "\\s*(\\[(?<taskIndex>[\\d]+)\\])?"
            + "\\s*(?<description>([^/]+)?)"
            + "(?:(/by|/at))?(?<date>([\\w\\s\\d/]+)?)");
    public static final Pattern NOTE_FORMAT = Pattern.compile("(\\{(?<noteIndex>[\\d]+)\\})?"
            + "\\s*(?<category>([\\w\\s\\d]+)?)"
            + "\\|?\\s*(?<description>([^/]+)?)");

    public Parser() {
        ui = new TextUi();
    }

    /**
     * method used to split up the user input and execute the required task.
     * @param fullCommand user input
     * @param scheduler TaskList object that commands are to be executed on
     */
    public void parseTasks(String fullCommand, TaskList scheduler) {
        this.scheduler = scheduler;
        Matcher matcher = COMMAND_FORMAT.matcher(fullCommand);
        if (matcher.find()) {
            splitTaskKeywords(matcher);
            if (isSafe) {
                executeCommand(command);
            }
        } else {
            ui.printErrorMsg2();
            scheduler.clearUI();
        }
    }

    /**
     * Parses the commands for handling notes.
     * @param noteCommand actual command for handling notes
     */
    public void splitNotesCommand(String noteCommand) {
        Matcher matcher = NOTE_FORMAT.matcher(noteCommand);
        if (matcher.find()) {
            if (matcher.group("noteIndex") == null) {
                noteIndex = null;
            } else {
                noteIndex = Integer.parseInt(matcher.group("noteIndex")) - 1;
            }
            category = matcher.group("category").trim();
            noteDescription = matcher.group("description").trim();
        } else {
            ui.printErrorMsg2();
            scheduler.clearUI();
            isSafe = false;
        }
    }

    /**
     * Method to initialize the various variables with the parsed command.
     * @param matcher contains the parsed command according to the above regex format
     */
    public void splitTaskKeywords(Matcher matcher) {
        command = matcher.group("commandWord");
        if (matcher.group("taskIndex") == null) {
            taskindex = null;
        } else {
            taskindex = Integer.parseInt(matcher.group("taskIndex")) - 1;
        }
        description = matcher.group("description").trim();
        if (!matcher.group("date").isEmpty()) {
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                date = LocalDateTime.parse(matcher.group("date").trim(), inputFormat);
            } catch (DateTimeParseException e) {
                ui.printWrongDate();
                isSafe = false;
                scheduler.clearUI();
            }
        }
    }

    /**
     * Method that executes the commands according to the appropriate command word.
     * @param command contains the main command word
     */
    public void executeCommand(String command) {
        try {
            switch (command.toLowerCase()) {
            case "todo":
            case "deadline":
            case "event":
            case "notebook":
                scheduler.addTask(command, description, false, date);
                scheduler.printNewTask();
                break;
            case "list":
                scheduler.listTasks();
                break;
            case "done":
                scheduler.completeTask(taskindex);
                break;
            case "delete":
                scheduler.removeTask(taskindex);
                break;
            case "find":
                scheduler.findTasks(description);
                break;
            case "shownotes":
                scheduler.shownotes(taskindex);
                break;
            case "findnotes":
                scheduler.findNotes(description);
                break;
            case "addnotes":
                splitNotesCommand(description);
                scheduler.getTasks().get(taskindex).addNote(category, noteDescription, date);
                scheduler.shownotes(taskindex);
                break;
            case "deletenotes":
                splitNotesCommand(description);
                scheduler.getTasks().get(taskindex).removeNote(noteIndex);
                scheduler.shownotes(taskindex);
                break;
            case "help":
                ui.printhelp();
                break;
            default:
                ui.printErrorMsg1();
                scheduler.clearUI();
            }
        } catch (NullPointerException e) {
            ui.printErrorMsg2();
            scheduler.clearUI();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no such items exists!");
            scheduler.clearUI();
        }

    }

    public String getCommand() {
        return command;
    }

    public Integer getTaskindex() {
        return taskindex;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Integer getNoteIndex() {
        return noteIndex;
    }

    public String getNoteDescription() {
        return noteDescription;
    }
}


