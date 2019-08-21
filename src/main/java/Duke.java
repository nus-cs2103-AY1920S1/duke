import java.util.Scanner;

public class Duke {

    final static String horizontalLine = "\t____________________________________________________________";

    public static void main(String[] args) {
        //program start
        startMessage();
        //import scanner + logic
        Scanner scanner = new Scanner(System.in);
        String[] taskList = new String[100]; //fixed-size array
        int lastTaskIndex = 0;
        while (true){
            String command = scanner.nextLine();
            if (command.equals("bye")){
                break;
            }
            else if (command.equals("list")){
                printList(taskList, lastTaskIndex);
                continue;
            }
            else {
                System.out.println(horizontalLine);
                System.out.println(formatText("added: " + command));
                System.out.println(horizontalLine);
                taskList[lastTaskIndex] = command;
                lastTaskIndex++;
            }
        }
        exitMessage();

    }

    private static void printList(String[] list , int index){
        System.out.println(horizontalLine);
        for (int i = 1; i <= index; i++){
            System.out.println(formatText(i + ". " + list[i-1]));
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
