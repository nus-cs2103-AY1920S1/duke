import java.util.Scanner;
import java.util.ArrayList;

enum Day{
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private static ArrayList<Task> list = new ArrayList<>();
    private static int listCounter = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);


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
                    String temp = "\t\t" + (i + 1) + "." + list.get(i).getItemInfo();
                    printList = printList + temp;
                    if(i != listCounter - 1) printList = printList + "\n";
                }

                System.out.println(processText(printList));
            }

            else if(readInput.toLowerCase().equals("todo")){
                String toDoItem = sc.nextLine();

                list.add(new ToDo(toDoItem, false));
                listCounter++;

                String tempPrint = addedTaskText();

                System.out.println(processText(tempPrint));
            }

            else if(readInput.toLowerCase().equals("event")){
                String input = sc.nextLine().trim();
                String[] tokenList = input.split("/");

                list.add(new Event(tokenList[0], tokenList[1], false));
                listCounter++;

                String tempPrint = addedTaskText();

                System.out.println(processText(tempPrint));
            }

            else if(readInput.toLowerCase().equals("deadline")){
                String input = sc.nextLine().trim();
                String[] tokenList = input.split("/");

                list.add(new Deadline(tokenList[0], tokenList[1], false));
                listCounter++;

                String tempPrint = addedTaskText();

                System.out.println(processText(tempPrint));
            }

            else if(readInput.toLowerCase().equals("done")){
                // Might need to implement exception handling
                int indexDone = sc.nextInt();

                list.get(--indexDone).setDone();

                String doneMessage = "Nice! I've marked this task as done: \n\t\t";
                System.out.println(processText(doneMessage + list.get(indexDone).getItemInfo()));
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

    private static String addedTaskText(){
        return "Got it. I've added this task:\n\t\t" +
                list.get(listCounter - 1).getItemInfo() +
                "\n\tNow you have " + listCounter + " tasks in the list.";
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
        this.taskItem = taskItem.trim();
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

    public String getItemInfo(){
        return getStatusIcon() + " " + getTaskItem();
    }
}

class ToDo extends Task{

    public ToDo(String toDo, boolean isDone){
        super(toDo.trim(), isDone);
    }

    @Override
    public String getStatusIcon(){
        if(isDone) return "[T][" + TICK + "]";
        else return "[T][" + CROSS + "]";
    }
}

class Deadline extends Task{
    private String deadline;
    private boolean isDeadlineProcessed;

    public Deadline(String event, String timing, boolean isDeadlineProcessed){
        super(event.trim(), false);
        this.deadline = timing.trim();
        this.isDeadlineProcessed = isDeadlineProcessed;
    }

    @Override
    public String getStatusIcon() {
        if (isDone) return "[D][" + TICK + "]";
        else return "[D][" + CROSS + "]";
    }

    public String getDeadline(){
        return this.deadline;
    }

    @Override
    public String getItemInfo(){
        if(!isDeadlineProcessed) {
            deadline = deadline.substring(3);
            isDeadlineProcessed = true;
        }
        return getStatusIcon() + " " + getTaskItem() + " (by: " + getDeadline() + ")";
    }
}

class Event extends Task {
    private String timing;
    private boolean isTimingProcessed;

    public Event(String event, String timing, boolean isDone) {
        super(event.trim(), isDone);
        this.timing = timing;
        this.isTimingProcessed = false;
    }

    @Override
    public String getStatusIcon() {
        if (isDone) return "[E][" + TICK + "]";
        else return "[E][" + CROSS + "]";
    }

    public String getTiming() {
        return timing;
    }

    @Override
    public String getItemInfo(){

        if(!isTimingProcessed) {
            timing = timing.substring(3);
            isTimingProcessed = true;
        }
        return getStatusIcon() + " " + getTaskItem() + " (at: " + getTiming() + ")";
    }

}