import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        int listCounter = 0;

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        // Start reading input
        while(sc.hasNextLine()){
            String readInput = sc.next();

            // Include case-insensitive bye
            if(readInput.toLowerCase().equals("bye")) {
                System.out.println(processText(goodbye));
                break;
            }

            else if (readInput.toLowerCase().equals("list")){
                String printList = "Here are the tasks in your list:\n";

                for(int i = 0; i < listCounter; i++){
                    String temp = "\t\t" + (i + 1) + "." +
                            list.get(i).getStatusIcon() + " " + list.get(i).getTaskItem();
                    printList = printList + temp + "\n";
                }

                System.out.println(processText(printList));
            }

            else if(readInput.toLowerCase().equals("todo")){
                String toDoItem = sc.nextLine();

                list.add(new ToDo(toDoItem, false));
                listCounter++;

                String tempPrint = "Got it. I've added this task: \n\t\t" +
                        list.get(listCounter - 1).getStatusIcon() + " " +
                        toDoItem + "\n\tNow you have " + listCounter + " tasks in the list." ;

                System.out.println(processText(tempPrint));
            }

            else if(readInput.toLowerCase().equals("done")){
                // Might need to implement exception handling
                int indexDone = sc.nextInt();

                list.get(--indexDone).setDone();

                String doneMessage = "Nice! I've marked this task as done: \n\t";
                System.out.println(processText(doneMessage +
                        list.get(indexDone).getStatusIcon() + " " +
                        list.get(indexDone).getTaskItem()));
            }

            else{
                // Read any remaining lines
                readInput = readInput + sc.nextLine();

                // Store content
                list.add(new Task(readInput, false));
                listCounter++;

                String processedInput = Duke.processText(readInput);
                System.out.println(processedInput);
            }
        }
    }

    // Add in Indentation and horizontal lines
    private static String processText(String input){
        return HORIZONTAL_LINE + "\n" + "\t" + input + "\n" + HORIZONTAL_LINE + "\n";
    }
}

class Task{
    protected static final String TICK = "\u2713";
    protected static final String CROSS = "\u2718";

    protected boolean isDone;
    protected String taskItem;

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

    public String getStatusIcon(){
        if(isDone) return "[" + TICK + "]";
        else return "[" + CROSS + "]";
    }

    public String getTaskItem(){
        return taskItem;
    }
}

class ToDo extends Task{

    public ToDo(String toDo, boolean isDone){
        super(toDo, isDone);
    }

    @Override
    public String getStatusIcon(){
        if(isDone) return "[T][" + TICK + "]";
        else return "[T][" + CROSS + "]";
    }
}