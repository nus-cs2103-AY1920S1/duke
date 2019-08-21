import java.util.Scanner;

public class Duke {

    final static String horizontalLine = "\t____________________________________________________________";

    public static void main(String[] args) {
        //program start
        startMessage();
        //import scanner + logic
        Scanner scanner = new Scanner(System.in);
        Task[] taskList = new Task[100]; //fixed-size array
        int lastTaskIndex = 0;
        while (true){
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");

            if (tokens[0].equals("bye")){
                break;
            }

            else if (tokens[0].equals("list")){
                printList(taskList, lastTaskIndex);
            }

            else if (tokens[0].equals("done")){
                doneTask(taskList, Integer.valueOf(tokens[1]));
            }

            else {
                System.out.println(horizontalLine);
                System.out.println(formatText("added: " + command));
                System.out.println(horizontalLine);
                taskList[lastTaskIndex] = new Task(command);
                lastTaskIndex++;
            }
        }
        exitMessage();
    }

    private static void doneTask(Task[] list, int index){
        list[index - 1].toggleDone();
        System.out.println(horizontalLine);
        System.out.println(formatText("Nice! I've marked this task as done:"));
        System.out.println(formatText("  [" + list[index - 1].getStatusIcon() + "] " + list[index - 1]));
        System.out.println(horizontalLine);
    }

    private static void printList(Task[] list , int index){
        System.out.println(horizontalLine);
        System.out.println(formatText("Here are the tasks in your list:"));
        for (int i = 1; i <= index; i++){
            System.out.println(formatText(i + ".[" + list[i-1].getStatusIcon() + "] " + list[i-1]));
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
