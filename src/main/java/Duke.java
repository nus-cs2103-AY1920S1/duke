import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    // Constructor
    public Duke() {
        taskList = new ArrayList<Task>();
    }

    public static void main(String[] args) throws DukeException {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        // First greetings
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("=====");
        Duke duke = new Duke();
        duke.run();
    }

    private boolean inProgram;
    private void run() throws DukeException {
        // Initialise Scanner object
        Scanner input = new Scanner(System.in);
        Task newTask;
        String taskDescription;
        // More general than taskDescription, includes "/by", "/at" info
        // Continually receive commands until exit
        inProgram = true;
        while (inProgram) {
            String userInput = input.nextLine();
            ArrayList<String> userInputArr = new ArrayList<String>(
                    // Split string by white spaces
                    Arrays.asList(userInput.split(" "))
            );
            // Identify command by first word
            switch (userInputArr.get(0)) {
            case ("bye"):
                displaySentence("Bye. Hope to see you again soon!");
                //Exit program
                inProgram = false;
                break;
            case ("list"):
                if (taskList.isEmpty()) {
                    displaySentence("You have no tasks yet!");
                } else {
                    displayTasks();
                }
                break;
            case ("todo"):
                // Empty description
                if (userInputArr.size() == 1) {
                    throw new EmptyDescriptionException("todo");
                } else {
                    // Splice back the description
                    taskDescription = String.join(" ",
                            userInputArr.subList(1, userInputArr.size()));
                    newTask = new ToDo(taskDescription);
                    taskList.add(newTask);
                    addTaskResponse(newTask);
                }
                break;
            case ("deadline"):
                addTaskWithSubcommand("deadline", userInputArr);
                break;
            case ("event"):
                addTaskWithSubcommand("event",userInputArr);
                break;
            case ("done"):
                // Empty/no list index of task provided
                if (userInputArr.size() == 1 || userInputArr.size() > 2) {
                    throw new DukeException("Please put the list index of the " +
                            "completed task after \"done\" and nothing else.");
                } else {
                    // Check if integer is provided
                    try {
                        int doneIdx = Integer.parseInt(userInputArr.get(1));
                        // Check if integer is within range of number of tasks
                        if (doneIdx > taskList.size() || doneIdx < 1) {
                            throw new DukeException("Integer is not within range of tasks.");
                        }
                        Task doneTask = taskList.get(doneIdx-1);
                        doneTask.markDone();
                        markDoneResponse(doneTask);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid integer after \"Done\".");
                    }
                }
                break;
            case ("delete"):
                // Empty/no list index of task provided
                if (userInputArr.size() == 1 || userInputArr.size() > 2) {
                    throw new DukeException("Please put the list index of the " +
                            "completed task after \"delete\" and nothing else.");
                } else {
                    // Check if integer is provided
                    try {
                        int deleteIdx = Integer.parseInt(userInputArr.get(1));
                        // Check if integer is within range of number of tasks
                        if (deleteIdx > taskList.size() || deleteIdx < 1) {
                            throw new DukeException("Integer is not within range of tasks.");
                        }
                        Task deletedTask = taskList.get(deleteIdx-1);
                        taskList.remove(deleteIdx-1);
                        deleteTaskResponse(deletedTask);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a valid integer after \"delete\".");
                    }
                }
                break;
            default:
                throw new UnknownCommandException();
            }
        }
    }

    //////////////////////
    // MODIFY TASK LIST //
    /////////////////////

    // For commands with subcommands
    // (deadline/by, event/at)
    private void addTaskWithSubcommand(String command, ArrayList<String> userInputArr) throws DukeException {
        String subCommand = command.equals("deadline") ? "/by" : "/at";
        // Empty Description
        // (only "deadline" or "deadline /by") or (only "event" or "deadline /at")
        if (userInputArr.size() == 1 || userInputArr.get(1).equalsIgnoreCase(subCommand)) {
            throw new EmptyDescriptionException(command);
        } else {
            int firstByIdx = userInputArr.indexOf(subCommand);
            int lastByIdx = userInputArr.lastIndexOf(subCommand);
            // No "/by" or multiple "/by"s provided
            if (firstByIdx == -1 || firstByIdx != lastByIdx) {
                throw new IncorrectInfoInputException(subCommand);
                // No description of "/by" or "/at"
            } else if (firstByIdx == userInputArr.size()-1) {
                throw new EmptyDescriptionException(subCommand);
            } else {
                // Splice words after first-word command and before "/by" or "/at"
                // NOTE: .subList(startIdx, endIdx) where endIdx is NOT inclusive
                String taskDescription = String.join(" ",
                        userInputArr.subList(1, firstByIdx));
                // Description for '/by', '/at'
                String subCommandDescription = String.join(" ",
                        userInputArr.subList(firstByIdx+1, userInputArr.size()));
                Task newTask = command.equals("deadline") ?
                        new Deadline(taskDescription, subCommandDescription) :
                        new Event(taskDescription, subCommandDescription);
                taskList.add(newTask);
                addTaskResponse(newTask);
            }
        }
    }

    ////////////////////
    // PRINT METHODS //
    //////////////////

    private String indentString = "    ";
    private String borderString = "-----";

    private String indentResponse(String response) {
        return indentString + response;
    }

    // Sandwich text between -----s
    private void displaySentence(String response) {
        System.out.println(borderString);
        System.out.println(indentResponse(response));
        System.out.println(borderString);
    }

    // List out added tasks ('list')
    private void displayTasks() {
        System.out.println(borderString);
        System.out.println(indentString + "Here are the tasks in your list:");
        for (Task task : taskList) {
            //Format: 1. [T/D/E][v/x] task-description (by/at: ...)
            System.out.println(indentString +
                    (taskList.indexOf(task)+1) + "." +
                    task.toString());
        }
        System.out.println(borderString);
    }

    // Acknowledgement receipt for commands which adds, deletes tasks
    private void addTaskResponse(Task task) {
        this.modifyTaskListResponse("Got it. I've adde this task:", task);
    }

    private void deleteTaskResponse(Task task) {
        this.modifyTaskListResponse("Noted. I've removed this task:", task);
    }

    // During additions and deletions to list
    private void modifyTaskListResponse(String message, Task task) {
        System.out.println(borderString);
        System.out.println(indentResponse(message));
        System.out.println(indentResponse("  " + task.toString()));
        System.out.println(indentResponse(
                "Now you have " + taskList.size() +
                        (taskList.size() == 1? " task":" tasks") +
                        " in the list."));
        System.out.println(borderString);
    }

    private void markDoneResponse(Task doneTask) {
        System.out.println(borderString);
        System.out.println(indentResponse("Nice! I've marked this task as done:"));
        System.out.println(indentResponse("  " + doneTask.toString()));
        System.out.println(borderString);
    }

}