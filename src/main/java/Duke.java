import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Exception;

/*
public class runDuke{
    public static void main(String[] args) throws CommandNotRecognizedException {
        Duke duke = new Duke();
        duke.runDuke();
    }
}
*/

public class Duke {
    private static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private static ArrayList<Task> list = new ArrayList<>();
    private static int listCounter = 0;

    //public void runDuke() throws CommandNotRecognizedException
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        // Start reading input
        while (sc.hasNextLine()) {
            try {
                String readInput = sc.next();

                // Include case-insensitive bye
                if (readInput.toLowerCase().equals("bye")) {
                    System.out.println(processText(goodbye));
                    break;
                }

                else if (readInput.toLowerCase().equals("list")) {

                    // Error Handling: ListEmpty
                    if(listCounter == 0){
                        // Clear buffer of scanner
                        String i = sc.nextLine();

                        throw new EmptyListException("");
                    }

                    String printList = "Here are the tasks in your list:\n";

                    for (int i = 0; i < listCounter; i++) {
                        String temp = "\t\t" + (i + 1) + "." + list.get(i).getItemInfo();
                        printList = printList + temp;
                        if (i != listCounter - 1) printList = printList + "\n";
                    }

                    System.out.println(processText(printList));
                }

                else if (readInput.toLowerCase().equals("todo")) {
                    String toDoItem = sc.nextLine().trim();

                    // Implies only word "deadline"
                    if(toDoItem.equals("")) {
                        throw new EmptyCommandField("todo");
                    }

                    list.add(new ToDo(toDoItem, false));
                    listCounter++;

                    String tempPrint = addedTaskText();
                    System.out.println(processText(tempPrint));
                }

                else if (readInput.toLowerCase().equals("event")) {
                    String input = sc.nextLine().trim();
                    String[] tokenList = input.split("/");

                    // Implies only word "event"
                    if(tokenList.length == 1) {
                        throw new EmptyCommandField("event");
                    }

                    // Format for item is incorrect
                    if(tokenList.length != 2){
                        throw new CommandFieldFormatException("event");
                    }

                    list.add(new Event(tokenList[0], tokenList[1], false));
                    listCounter++;

                    String tempPrint = addedTaskText();
                    System.out.println(processText(tempPrint));
                }

                else if (readInput.toLowerCase().equals("deadline")) {
                    String input = sc.nextLine().trim();
                    String[] tokenList = input.split("/");

                    // Implies only word "deadline"
                    if(tokenList.length == 1) {
                        throw new EmptyCommandField("deadline");
                    }

                    // Format for item is incorrect
                    if(tokenList.length != 2){
                        throw new CommandFieldFormatException("deadline");
                    }

                    list.add(new Deadline(tokenList[0], tokenList[1], false));
                    listCounter++;

                    String tempPrint = addedTaskText();

                    System.out.println(processText(tempPrint));
                }

                else if (readInput.toLowerCase().equals("done")) {

                    // Cannot perform done in zero list
                    if(listCounter == 0){
                        // Clear buffer of scanner
                        String i = sc.nextLine();

                        throw new EmptyListException("Cannot perform done.");
                    }

                    int indexDone = sc.nextInt();

                    // If indexDone exceed listCounter
                    if(indexDone > listCounter || indexDone < 0){
                        throw new InvalidNumberException("Error exceeding item number");
                    }

                    list.get(--indexDone).setDone();

                    String doneMessage = "Nice! I've marked this task as done: \n\t\t";
                    System.out.println(processText(doneMessage + list.get(indexDone).getItemInfo()));
                }

                /*
                README:
                ECHO FUNCTION HAS BEEN REPLACED WITH COMMAND ECHO,
                TO FACILITATE COMMANDS THAT ARE NOT RECOGNIZABLE.
                 */
                else if (readInput.toLowerCase().equals("echo")) {
                    // Read any remaining lines
                    String echoInput = sc.nextLine().trim();

                    if(echoInput.equals("")){
                        throw new EmptyCommandField("echo");
                    }

                    // Store content
                    list.add(new Task(echoInput, false));
                    listCounter++;

                    String processedInput = Duke.processText(echoInput);
                    System.out.println(processedInput);
                }

                else if(readInput.toLowerCase().equals("delete")){

                    // Incorrect format for delete
                    if(!sc.hasNextInt()){
                        sc.nextLine();
                        throw new CommandFieldFormatException("delete");
                    }

                    int numberToDelete = sc.nextInt();

                    // Make sure number is valid
                    if(numberToDelete > listCounter || numberToDelete < 1){
                        throw new InvalidNumberException("Number to delete does not exist");
                    }

                    Task deletedTask = list.remove(numberToDelete - 1);
                    listCounter--;
                    String tempPrint = "Noted. I've removed this task:\n\t\t" +
                            deletedTask.getItemInfo() +
                            "\n\tNow you have "+ listCounter + " tasks in the list.";
                    System.out.println(processText(tempPrint));
                }

                else {
                    // Not recognized command
                    throw new CommandNotRecognizedException();
                }
            }

            catch (CommandNotRecognizedException c) {
                // Clear buffer of scanner
                String i = sc.nextLine();

                System.out.println(processText("\u263A OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
            catch (EmptyCommandField e){
                System.out.println(processText("\u263A The description of " + e.getMessage() + " cannot be empty."));
            }
            catch (EmptyListException l){
                System.out.println(processText("\u263A List is empty! " + l.getMessage()));
            }
            catch (CommandFieldFormatException f){
                System.out.println(processText("\u263A Description format is incorrect for " + f.getMessage() + "."));
            }
            catch (InvalidNumberException n){
                System.out.println(processText("\u263A Invalid input number. " + n.getMessage()));

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

class CommandNotRecognizedException extends Exception{
    public CommandNotRecognizedException(){
        super("Error");
    }
}

// For:
// (1) Error printing empty list
// (2) Error performing done on empty list
class EmptyListException extends Exception{
    public EmptyListException(String info){
        super(info);
    }
}

// Format of command is incorrect: not using "/"
class CommandFieldFormatException extends Exception{
    public CommandFieldFormatException(String command){
        super(command);
    }
}

// Exceeded number of items in list
class InvalidNumberException extends Exception{
    public InvalidNumberException(String info){
        super(info);
    }
}

class EmptyCommandField extends Exception {
    public EmptyCommandField(String command){
        super(command);
    }
}