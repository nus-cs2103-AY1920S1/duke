import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Represents a user interface that deals with the user.
 */
public class Ui {

    private static final String LOADING_ERROR = "No existing task list can be loaded. New task list will be created.";

    private Storage mainStorage;
    private TaskList mainTaskList;

    public void setStorage(Storage storage) {
        mainStorage = storage;
    }

    public void setList(TaskList taskList) {
        mainTaskList = taskList;
    }

    /**
     * Sets up the user interface to take in user commands.
     *
     * @param storage Storage handling.
     * @param taskList Task handling.
     */
    public void initiate(Storage storage, TaskList taskList) throws IOException, ParseException {
        mainStorage = storage;
        mainTaskList = taskList;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = String.format("\n%s\n%s\n", "Hello! I'm Duke", "What can I do for you?");
        System.out.println(logo + greeting);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(getResponse(input));

            write();

            if (sc.hasNext()) {
                input = sc.nextLine();
            } else {
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out loading error message.
     */
    public void showLoadingError() {
        System.out.println(LOADING_ERROR);
    }

    /**
     * Returns task addition response given specified list of task.
     *
     * @param taskList List of task.
     * @return Task addition response.
     */
    public String getTaskAddition(TaskList taskList) {
        StringBuilder message = new StringBuilder();
        message.append("Got it. I've added this task:\n");
        message.append(taskList.getTask());

        return message.toString();
    }

    /**
     * Returns chatbot response based on user commands.
     *
     * @param input User commands.
     * @return Chatbot response.
     */
    public String getResponse(String input) throws ParseException {
        StringBuilder response = new StringBuilder();

        if (!input.equals("bye")) {
            String[] parseInfo = Parser.parseCommand(input);
            int currentNum = mainTaskList.getNumTask();

            switch (parseInfo[0]) {
            case "done":
                int doneNum = Integer.valueOf(parseInfo[1]);

                if (doneNum > 0 && doneNum <= currentNum) {
                    response.append("Nice! I've marked this task as done:\n");
                    response.append(mainTaskList.getDoneTask(parseInfo[1]) + "\n");
                } else {
                    response.append("Task does not exist");
                }

                break;
            case "delete":
                int delNum = Integer.valueOf(parseInfo[1]);

                if (delNum > 0 && delNum <= currentNum) {
                    response.append("Noted. I've removed this task:\n");
                    response.append(mainTaskList.deleteTask(parseInfo[1]) + "\n");
                } else {
                    response.append("Task does not exist");
                }

                break;
            case "todo":
                mainTaskList.addTodoTask(parseInfo[1]);
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "deadline":
                mainTaskList.addDeadlineTask(Parser.parseDetails(parseInfo[1]));
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "event":
                mainTaskList.addEventTask(Parser.parseDetails(parseInfo[1]));
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "read":
                response.append("Data source for reading has been changed\n");

                try {
                    setList(new TaskList(new Storage("data/" + parseInfo[1]).load()));
                } catch (FileNotFoundException e) {
                    response.append(LOADING_ERROR + "\n");
                    setList(new TaskList());
                }
                break;
            case "write":
                response.append("Data source for writing has been changed\n");
                setStorage(new Storage("data/" + parseInfo[1]));
                break;
            case "list":
                response.append("Here are the tasks in your list:\n");
                response.append(mainTaskList);
                break;
            case "help":
                response.append(getHelpMessage());
                break;
            case "find":
                response.append("Here are the matching tasks in your list:\n");
                for (int i = 0, j = 0; i < mainTaskList.getNumTask(); i++) {
                    Task current = mainTaskList.getTask(i);

                    if (current.hasKeyword(parseInfo[1])) {
                        response.append(String.format("%d.%s", ++j, current) + "\n");
                    }
                }
                break;
            default:
                try {
                    if (input.matches("todo|deadline|event|done|find|read|write")) {
                        String message = String.format("The description of a %s cannot be empty.", input);
                        throw new DukeException(message);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException exception) {
                    response.append(exception.getMessage());
                }
            }

            if (currentNum != mainTaskList.getNumTask()) {
                response.append(String.format("Now you have %d tasks in the list.\n", mainTaskList.getNumTask()));
            }
        } else {
            response.append("Bye. Hope to see you again soon!\n");
        }

        return response.toString();
    }

    /**
     * Calls for <code>Storage</code> to overwrite stored list of tasks.
     */
    public void write() throws IOException {
        mainStorage.overWrite(mainTaskList.getList());
    }

    private String getHelpMessage() {
        return "List of available commands:\n\n" +
                "deadline [subject] /by [date/time]\n" +
                "\tAdds task that needs to be done before a\n" +
                "\tspecific date/time.\n" +
                "delete [number]\n" +
                "\tRemoves task from list.\n" +
                "done [number]\n" +
                "\tMarks a task as done.\n" +
                "event [subject] /at [date/time]\n" +
                "\tAdds task that starts and ends at a specific\n" +
                "\tdate/time.\n" +
                "find [keyword]\n" +
                "\tSearches for task by keyword.\n" +
                "list\n" +
                "\tDisplays current list of tasks.\n" +
                "read [file]\n" +
                "\tChanges data source for reading.\n" +
                "todo [subject]\n" +
                "\tAdds task without any date/time attached to\n" +
                "\tit.\n" +
                "write [file]\n" +
                "\tChanges data source for writing.\n";
    }
}