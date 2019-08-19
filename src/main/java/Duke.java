import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    //Constructor
    public Duke() {
        taskList = new ArrayList<>();
    }
    public static void main(String[] args) {
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
    private void run() {
        //Initialise Scanner object
        Scanner input = new Scanner(System.in);
        String userInput;
        Task newTask;
        //Continually receive commands until exit
        inProgram = true;
        while (inProgram) {
            String firstCommand = input.next();
            String taskDescription;
            //Identify command by first word
            switch (firstCommand) {
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
                    //TODO check for valid input (no whitespace only)
                    if (input.hasNext()) {
                        //Ignore starting whitespace
                        taskDescription = input.nextLine().substring(1);
                        newTask = new ToDo(taskDescription);
                        taskList.add(newTask);
                        displayResponse(newTask);
                    } else {
                        display("Please write something after \"todo\"!");
                    }
                    break;
                case ("deadline"):
                    //TODO check for valid input
                    // (no whitespace only, has "/by")
                    if (input.hasNext()) {
                        userInput = input.nextLine().substring(1);
                        int byIdx = userInput.indexOf("/by");
                        taskDescription = userInput.substring(0, byIdx-1);
                        String by = userInput.substring(byIdx+4);
                        newTask = new Deadline(taskDescription, by);
                        taskList.add(newTask);
                        displayResponse(newTask);
                    } else {
                        display("Please write something after \"deadline\"!");
                    }
                    break;
                case ("event"):
                    //TODO check for valid input
                    // (no whitespace only, has "/at")
                    if (input.hasNext()) {
                        userInput = input.nextLine().substring(1);
                        int atIdx = userInput.indexOf("/at");
                        taskDescription = userInput.substring(0, atIdx-1);
                        String at = userInput.substring(atIdx+4);
                        newTask = new Event(taskDescription, at);
                        taskList.add(newTask);
                        displayResponse(newTask);
                    } else {
                        display("Please write something after \"event\"!");
                    }
                    break;
                case ("done"):
                    //TODO check for valid inputs
                    // (integer valid in list)
                    if (input.hasNext()) {
                        int doneNum = input.nextInt();
                        Task doneTask = taskList.get(doneNum-1);
                        doneTask.markDone();
                        displayMarkDone(doneTask);
                    } else {
                        display("Please write something after \"event\"!");
                    }
                    break;
                default:
                    display("\"" + firstCommand + "\"" +
                            " is not a valid command. Try again.\n\n" +
                            indentString +
                            "List of valid commands: \n" +
                            indentString +
                            "todo, deadline, event, list, done, bye");
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
        System.out.println(indentString + task.toString());
        System.out.println(indentString +
                "Now you have " + taskList.size() +
                (taskList.size() == 1? " task":" tasks") +
                " in the list.");
        System.out.println("-----");
    }

    private void displayMarkDone(Task doneTask) {
        System.out.println("-----");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + doneTask.toString());
        System.out.println("-----");
    }
}