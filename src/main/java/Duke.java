import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    //Constructor
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
        //First greetings
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("=====");
        Duke duke = new Duke();
        duke.run();
    }

    private boolean inProgram;
    private void run() throws DukeException {
        //Initialise Scanner object
        Scanner input = new Scanner(System.in);
        Task newTask;
        String taskDescription;
        //More general than taskDescription, includes "/by", "/at" info
        String taskInformation;
        // E.g. /by, /at
        String subCommand;
        //Continually receive commands until exit
        inProgram = true;
        while (inProgram) {
            String userInput = input.nextLine();
            ArrayList<String> userInputArr = new ArrayList<String>(
                    //Split string by white spaces
                    Arrays.asList(userInput.split(" "))
            );
            //Identify command by first word
            switch (userInputArr.get(0)) {
                case ("bye"):
                    display("Bye. Hope to see you again soon!");
                    //Exit program
                    inProgram = false;
                    break;
                case ("list"):
                    if (taskList.isEmpty()) {
                        display("You have no tasks yet!");
                    } else {
                        displayTasks();
                    }
                    break;
                case ("todo"):
                    //Empty description
                    if (userInputArr.size() == 1) {
                        throw new EmptyDescriptionException("todo");
                    } else {
                        //Splice back the description
                        taskDescription = String.join(" ",
                                userInputArr.subList(1, userInputArr.size()));
                        newTask = new ToDo(taskDescription);
                        taskList.add(newTask);
                        displayResponse(newTask);
                    }
                    break;
                case ("deadline"):
                    subCommand = "/by";
                    //Empty Description (only "deadline" or "deadline /by")
                    if (userInputArr.size() == 1 || userInputArr.get(1).equalsIgnoreCase(subCommand)) {
                        throw new EmptyDescriptionException("deadline");
                    } else {
                        int firstByIdx = userInputArr.indexOf(subCommand);
                        int lastByIdx = userInputArr.lastIndexOf(subCommand);
                        //No "/by" or multiple "/by"s provided
                        if (firstByIdx == -1 || firstByIdx != lastByIdx) {
                            throw new IncorrectInfoInputException(subCommand);
                        //No description of "/by"
                        } else if (firstByIdx == userInputArr.size()-1) {
                            throw new EmptyDescriptionException(subCommand);
                        } else {
                            //Splice words after first-word command and before "/by"
                            //NOTE: .subList(startIdx, endIdx) where endIdx is NOT inclusive
                            taskDescription = String.join(" ",
                                    userInputArr.subList(1, firstByIdx));
                            String byDescription = String.join(" ",
                                    userInputArr.subList(firstByIdx+1, userInputArr.size()));
                            newTask = new Deadline(taskDescription, byDescription);
                            taskList.add(newTask);
                            displayResponse(newTask);
                        }
                    }
                    break;
                case ("event"):
                    subCommand = "/at";
                    //Empty Description (only "deadline" or "deadline /by")
                    if (userInputArr.size() == 1 || userInputArr.get(1).equalsIgnoreCase(subCommand)) {
                        throw new EmptyDescriptionException("event");
                    } else {
                        int firstByIdx = userInputArr.indexOf(subCommand);
                        int lastByIdx = userInputArr.lastIndexOf(subCommand);
                        //No "/by" or multiple "/by"s provided
                        if (firstByIdx == -1 || firstByIdx != lastByIdx) {
                            throw new IncorrectInfoInputException(subCommand);
                            //No description of "/by"
                        } else if (firstByIdx == userInputArr.size()-1) {
                            throw new EmptyDescriptionException(subCommand);
                        } else {
                            //Splice words after first-word command and before "/at"
                            taskDescription = String.join(" ",
                                    userInputArr.subList(1, firstByIdx));
                            String atDescription = String.join(" ",
                                    userInputArr.subList(firstByIdx+1, userInputArr.size()));
                            newTask = new Event(taskDescription, atDescription);
                            taskList.add(newTask);
                            displayResponse(newTask);
                        }
                    }
                    break;
                case ("done"):
                    //Empty/no list index of task provided
                    if (userInputArr.size() == 1 || userInputArr.size() > 2) {
                        throw new DukeException("Please put the list index of the " +
                                "completed task after \"done\" and nothing else.");
                    } else {
                        //Check if integer is provided
                        try {
                            int doneIdx = Integer.parseInt(userInputArr.get(1));
                            //Check if integer is within range of number of tasks
                            if (doneIdx > taskList.size() || doneIdx < 1) {
                                throw new DukeException("Integer is not within range of tasks.");
                            }
                            Task doneTask = taskList.get(doneIdx-1);
                            doneTask.markDone();
                            displayMarkDone(doneTask);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please enter a valid integer after \"Done\".");
                        }
                    }
                    break;
                default:
                    throw new UnknownCommandException();
            }
        }
    }

    private String indentString = "    ";

    //Sandwich text between -----s
    private void display(String response) {
        System.out.println("-----");
        System.out.println(indentString + response);
        System.out.println("-----");
    }

    //List out added tasks ('list')
    private void displayTasks() {
        System.out.println("-----");
        System.out.println(indentString + "Here are the tasks in your list:");
        for (Task task : taskList) {
            //Format: 1. [T/D/E][v/x] task-description (by/at: ...)
            System.out.println(indentString +
                    (taskList.indexOf(task)+1) + "." +
                    task.toString());
        }
        System.out.println("-----");
    }

    //Acknowledgement receipt for commands ('todo'/'deadline'/'event')
    private void displayResponse(Task task) {
        System.out.println("-----");
        System.out.println(indentString + "Got it. I've added this task:");
        System.out.println(indentString + "  " + task.toString());
        System.out.println(indentString +
                "Now you have " + taskList.size() +
                (taskList.size() == 1? " task":" tasks") +
                " in the list.");
        System.out.println("-----");
    }

    private void displayMarkDone(Task doneTask) {
        System.out.println("-----");
        System.out.println(indentString + "Nice! I've marked this task as done:");
        System.out.println(indentString + "  " + doneTask.toString());
        System.out.println("-----");
    }

}