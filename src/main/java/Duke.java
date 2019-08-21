import java.util.Scanner;

public class Duke {

    final private static String horizontalLine = "\t____________________________________________________________";
    private static Task[] taskList = new Task[100];
    private static int lastTaskIndex = 0;

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
                printList(taskList, lastTaskIndex);
            }

            else if (tokens[0].equals("done")){
                try {
                    doneTask(taskList, Integer.parseInt(tokens[1]));
                }
                catch (ArrayIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("Huh, what do you wanna do?"));
                    System.out.println(horizontalLine);
                }
            }

            else if (tokens[0].equals("todo")){
                try {
                    addToDo(input.substring(5));
                    lastTaskIndex++;
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
                    addDeadline(dateSplit[0].substring(9), dateSplit[dateSplit.length - 1]);
                    lastTaskIndex++;
                }
                catch (StringIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The description of deadline cannot be empty :("));
                    System.out.println(horizontalLine);
                }
            }
            else if (tokens[0].equals("event")){
                try {
                    String[] dateSplit = input.split(" /at ");
                    addEvent(dateSplit[0].substring(6), dateSplit[dateSplit.length - 1]);
                    lastTaskIndex++;
                }
                catch (StringIndexOutOfBoundsException error){
                    System.out.println(horizontalLine);
                    System.out.println(formatText("The description of event cannot be empty :("));
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
        ToDo toDoTask = new ToDo(task, lastTaskIndex+1);
        taskList[lastTaskIndex] = toDoTask;
        System.out.println(formatText("  " + toDoTask ));
        System.out.println(formatText("Now you have " + Task.getTotal() + " tasks in the list."));
        System.out.println(horizontalLine);
    }
    private static void addDeadline(String task, String by){
        System.out.println(horizontalLine);
        System.out.println(formatText("Got it. I've added this task:"));
        Deadline deadlineTask = new Deadline(task,lastTaskIndex+1,by);
        taskList[lastTaskIndex] = deadlineTask;
        System.out.println(formatText("  " + deadlineTask ));
        System.out.println(formatText("Now you have " + Task.getTotal() + " tasks in the list."));
        System.out.println(horizontalLine);
    }

    private static void addEvent(String task, String by){
        System.out.println(horizontalLine);
        System.out.println(formatText("Got it. I've added this task:"));
        Event eventTask = new Event(task,lastTaskIndex+1, by);
        taskList[lastTaskIndex] = eventTask;
        System.out.println(formatText("  " + eventTask ));
        System.out.println(formatText("Now you have " + Task.getTotal() + " tasks in the list."));
        System.out.println(horizontalLine);
    }

    private static void doneTask(Task[] list, int index){
        list[index - 1].toggleDone();
        System.out.println(horizontalLine);
        System.out.println(formatText("Nice! I've marked this task as done:"));
        System.out.println(formatText("  " + list[index - 1]));
        System.out.println(horizontalLine);
    }

    private static void printList(Task[] list , int index){
        System.out.println(horizontalLine);
        System.out.println(formatText("Here are the tasks in your list:"));
        for (int i = 1; i <= index; i++){
            Task task = list[i-1];
            System.out.println(formatText(task.getId() + "." + task.toString()));
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

}
