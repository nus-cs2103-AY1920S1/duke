import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private static final String TICK = "?";
    private static final String CROSS = "?";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int listCounter = 0;

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        // Start reading input
        while(sc.hasNextLine()){
            String readInput = sc.nextLine();

            // Include case-insensitive bye
            if(readInput.toLowerCase().equals("bye")) {
                System.out.println(process(goodbye));
                break;
            }
            else if (readInput.toLowerCase().equals("list")){
                System.out.println(HORIZONTAL_LINE);
                for(int i = 0; i < listCounter; i++)
                    System.out.println("\t" + (i + 1)  + ". " + list.get(i));
                System.out.println(HORIZONTAL_LINE);
            }
            else{
                // Store content
                list.add(readInput);
                listCounter++;

                String processedInput = Duke.process(readInput);
                System.out.println(processedInput);
            }
        }
    }

    // Add in Indentation and horizontal lines
    private static String process(String input){
        return HORIZONTAL_LINE + "\n" + "\t" + input + "\n" + HORIZONTAL_LINE + "\n";
    }
}

class Task{
    private boolean isDone;
    private String taskItem;

    // Default Constructor
    public Task(){
        isDone = false;
    }

    // Non-default Constructor
    public Task(String taskItem, boolean isDone){
        this.isDone = isDone;
        this.taskItem = taskItem;
    }

    public void setDone(){
        isDone = true;
    }

    public boolean getCompletedStatus(){
        return isDone;
    }

    public String getTaskItem(){
        return taskItem;
    }
}
