import javafx.application.Platform;

import java.util.Scanner;

/**
 * Represents a user interface that deals with the user.
 */
public class Ui {

    private final String LOADING_ERROR = "No existing task list can be loaded. New task list will be created.";

    private Storage mainStorage;
    private TaskList mainTaskList;

    /**
     * Sets up the user interface to take in user commands.
     *
     * @param storage Storage handling.
     * @param taskList Task handling.
     */
    public void initiate(Storage storage, TaskList taskList) {
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
        Platform.exit();
    }

    public void setStorage(Storage storage) {
        mainStorage = storage;
    }

    public void setList(TaskList taskList) {
        mainTaskList = taskList;
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
     * Prints out loading error message.
     */
    public void showLoadingError() {
        System.out.println(LOADING_ERROR);
    }

    /**
     * Returns chatbot response based on user commands.
     *
     * @param input User commands.
     * @return Chatbot response.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();

        if (!input.equals("bye")) {
            String[] parseInfo = Parser.parseCommand(input);
            int currentNum = mainTaskList.getNumTask();

            switch (parseInfo[0]) {
            case "DONE":
                response.append("Nice! I've marked this task as done:\n");
                response.append(mainTaskList.getDoneTask(parseInfo[1]) + "\n");
                break;
            case "DELETE":
                response.append("Noted. I've removed this task:\n");
                response.append(mainTaskList.deleteTask(parseInfo[1]) + "\n");
                break;
            case "TODO":
                mainTaskList.addTodoTask(parseInfo[1]);
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "DEADLINE":
                mainTaskList.addDeadlineTask(Parser.parseDetails(parseInfo[1]));
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "EVENT":
                mainTaskList.addEventTask(Parser.parseDetails(parseInfo[1]));
                response.append(getTaskAddition(mainTaskList) + "\n");
                break;
            case "LIST":
                response.append("Here are the tasks in your list:\n");
                for (int i = 0; i < mainTaskList.getNumTask(); i++) {
                    response.append(String.format("%d.%s", i + 1, mainTaskList.getTask(i)) + "\n");
                }
                break;
            case "FIND":
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
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")
                            || input.equals("done") || input.equals("find")) {
                        String message = String.format("The description of a %s cannot be empty.", input);
                        throw new InsufficientArgumentException(message);
                    } else {
                        throw new InvalidArgumentException("I'm sorry, but I don't know what that means :-(");
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
    public void write() {
        mainStorage.overWrite(mainTaskList.getList());
    }
}