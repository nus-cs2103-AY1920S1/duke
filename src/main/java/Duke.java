import java.util.Scanner;

public class Duke {
    //Constructor
    private String[] taskList;
    private int numTask;
    public Duke() {
        //Fixed size array
        taskList = new String[100];
        numTask = 0;
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

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
            String userCommand = input.nextLine();
            switch (userCommand) {
                case ("bye"):
                    display("Bye. Hope to see you again soon!");
                    //Exit program
                    inProgram = false;
                    break;
                case ("list"):
                    if (numTask == 0) {
                        display("You have no tasks yet!");
                    } else {
                        System.out.println("-----");
                        //List out added tasks
                        for (int i = 0; i < numTask; i++) {
                            System.out.println((i+1) + ". " + taskList[i]);
                        }
                        System.out.println("-----");
                    }
                    break;
                default:
                    taskList[numTask] = userCommand;
                    numTask++;
                    display("added: " + userCommand);
            }
        }
    }

    private void display(String response) {
        System.out.println("-----");
        System.out.println("    " + response);
        System.out.println("-----");
    }


}
