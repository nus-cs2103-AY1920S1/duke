package parser;

import Ui.TextUi;
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
    private boolean isSafe;
    private TextUi ui;

    public Parser(){
        ui = new TextUi();
    };

    /**
     * method used to split up the user input and execute the required task
     * @param fullCommand user input
     * @param scheduler TaskList object that commands are to be executed on
     * @param isLoading boolean variable to check if the input is from a user or save file (to stop some print actions)
     */

    public void parse(String fullCommand, TaskList scheduler , boolean isLoading){
        isSafe = true;
        Pattern command_format = Pattern.compile("(?<commandWord>\\w+)"
                + "\\s*(?<completionStatus>(\\[[01]\\])?)"
                + "\\s*(?<description>([\\w\\s\\d]+)?)"
                + "(?:(/by|/at))?(?<date>([\\w\\s\\d/]+)?)");
        Matcher matcher = command_format.matcher(fullCommand);

        if (matcher.find()) {
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

            if (isSafe) {
                switch (command) {
                case "todo":
                case "deadline":
                case "event":
                    scheduler.addTask(command, description, isDone, date);
                    if (!isLoading) {
                        scheduler.printnewtask();
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
        } else {
            ui.printErrorMsg2();
            isSafe = false;
        }

    }


    public boolean isSafe() {
        return isSafe;
    }

}


