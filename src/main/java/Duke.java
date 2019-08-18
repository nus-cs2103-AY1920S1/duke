import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //Constructor
    private ArrayList<Task> taskList;
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
        //Continually receive commands until exit
        inProgram = true;
        while (inProgram) {
            String userInput = input.nextLine();
            //Split input by " " to identify command by first word
            String[] splitInput = userInput.split(" ");
            switch (splitInput[0]) {
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
                case ("done"):
                    //TODO check for valid inputs (integer valid in list)
                    //Number of task in list to mark done
                    int taskNum = Integer.parseInt(splitInput[1]);
                    displayMarkDone(taskNum);
                    break;
                default:
                    taskList.add(new Task(userInput));
                    display("added: " + userInput);
            }
        }
    }

    private void display(String response) {
        System.out.println("-----");
        System.out.println("    " + response);
        System.out.println("-----");
    }

    //List out added tasks
    private void displayTasks() {
        System.out.println("-----");
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println("    " +
                    (taskList.indexOf(task)+1) + "." +
                    "[" + task.getStatusIcon() + "] " +
                    task.description);
        }
        System.out.println("-----");
    }

    private void displayMarkDone(int doneIdx) {
        Task doneTask = taskList.get(doneIdx-1);
        doneTask.markDone();
        System.out.println("-----");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " +
                "[" + doneTask.getStatusIcon() + "] " +
                doneTask.description);
        System.out.println("-----");
    }
}