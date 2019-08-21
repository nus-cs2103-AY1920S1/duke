import java.util.Scanner;
public class Duke {
    private static int numTasks = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] taskList = new String[100];

        printReply("Hello! I'm Duke\n\tWhat can I do for you?");
        String command = sc.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list"))
                printList(taskList);
            else
                addToList(command, taskList);
            command = sc.next();
        }
        printReply("Bye. Hope to see you again soon!");
    }
    public static void printReply(String reply){
        System.out.println("\t________________________________________\n\t" + reply
                           + "\n\t________________________________________");
    }
    public static void addToList(String task, String[] taskList){
        taskList[numTasks++] = task;
        printReply("added: " + task);
    }
    public static void printList(String[] taskList){
        String listReply = "";
        for(int i=0; i<numTasks; i++){
            listReply += (i+1) + ". " + taskList[i];
            if(i != numTasks-1)
                listReply += "\n\t";
            printReply(listReply);
        }
    }
}