import java.util.MissingFormatArgumentException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    final private static String horizontalLine = "\t____________________________________________________________";
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws InvalidCommandException {
        //program start
        startMessage();
        //import scanner + logic
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("bye")){
                break;
            }

            else if (tokens[0].equals("list")){
                printList();
            }

            else if (tokens[0].equals("done")){
                try {
                    doneTask(Integer.parseInt(tokens[1]));
                }
                catch (ArrayIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("Huh, what do you wanna do?"));
                    System.out.println(horizontalLine);
                }
                catch (IllegalArgumentException error2){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("But this task has already been done!"));
                    System.out.println(horizontalLine);
                }
            }

            else if (tokens[0].equals("todo")){
                try {
                    addToDo(input.substring(5));
                }
                catch (StringIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The description of todo cannot be empty :("));
                    System.out.println(horizontalLine);
                }

            }
            else if (tokens[0].equals("deadline")){
                try {
                    String[] dateSplit = input.split(" /by ");
                    String deadlineDesc = dateSplit[0].substring(9);
                    checkValidInput("deadline",input);
                    addDeadline(deadlineDesc, dateSplit[dateSplit.length - 1]);
                }
                catch (StringIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The description of deadline cannot be empty :("));
                    System.out.println(horizontalLine);
                }
                catch (MissingFormatArgumentException error2){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("No deadline detected!"));
                    System.out.println(horizontalLine);
                }
            }
            else if (tokens[0].equals("event")){
                try {
                    String[] dateSplit = input.split(" /at ");
                    String eventDesc = dateSplit[0].substring(6);
                    checkValidInput("event",input);
                    addEvent(eventDesc, dateSplit[dateSplit.length - 1]);
                }
                catch (StringIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The description of event cannot be empty :("));
                    System.out.println(horizontalLine);
                }
                catch (MissingFormatArgumentException error2){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The event must have a location!"));
                    System.out.println(horizontalLine);
                }
            }

            else {
                throw new InvalidCommandException("Invalid Command :( Duke is sad. Please try again.");
            }
        }
        exitMessage();
    }

    private static void addToDo(String task){
        System.out.println(horizontalLine);
        System.out.println(formatText("Got it. I've added this task:"));
        ToDo toDoTask = new ToDo(task);
        taskList.add(toDoTask);
        System.out.println(formatText("  " + toDoTask ));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(horizontalLine);
    }
    private static void addDeadline(String task, String by){
        System.out.println(horizontalLine);
        System.out.println(formatText("Got it. I've added this task:"));
        Deadline deadlineTask = new Deadline(task,by );
        taskList.add(deadlineTask);
        System.out.println(formatText("  " + deadlineTask ));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(horizontalLine);
    }

    private static void addEvent(String task, String at){
        System.out.println(horizontalLine);
        System.out.println(formatText("Got it. I've added this task:"));
        Event eventTask = new Event(task, at);
        taskList.add(eventTask);
        System.out.println(formatText("  " + eventTask ));
        System.out.println(formatText("Now you have " + taskList.size() + " tasks in the list."));
        System.out.println(horizontalLine);
    }

    private static void doneTask(int index) throws IllegalArgumentException{
        if (taskList.get(index-1).getIsDone()){
            throw new IllegalArgumentException("Task has already been done");
        }
        taskList.get(index-1).toggleDone();
        System.out.println(horizontalLine);
        System.out.println(formatText("Nic  e! I've marked this task as done:"));
        System.out.println(formatText("  " + taskList.get(index-1)));
        System.out.println(horizontalLine);
    }

    private static void printList(){
        System.out.println(horizontalLine);
        System.out.println(formatText("Here are the tasks in your list:"));
        int index = 1;
        for (Task task: taskList){
            System.out.println(formatText(index + "." + task.toString()));
            index++;
        }
        System.out.println(horizontalLine);
    }
    private static String formatText(String text) {
        return "\t " + text;
    }

    private static void startMessage() {
        System.out.println(horizontalLine);
        System.out.println(formatText("Hello! I'm Duke"));
        System.out.println(formatText("What can I do for you?"));
        System.out.println(horizontalLine);
    }
    private static void exitMessage(){
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(formatText(exitMessage));
        System.out.println(horizontalLine);
    }

    private static void checkValidInput(String command, String input) throws MissingFormatArgumentException{
        switch(command){
            case "deadline":
                if (!input.contains(" /by ")){
                    throw new MissingFormatArgumentException("Missing deadline");
                }
                break;
            case "event":
                if (!input.contains(" /at ")){
                    throw new MissingFormatArgumentException("Missing deadline");
                }
                break;
            default:
                return;
        }
        return;
    }

}
