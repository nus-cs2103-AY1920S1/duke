import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] taskList = new String[100];
        Integer numTasks = new Integer(0);

        printReply("Hello! I'm Duke\n\tWhat can I do for you?");
        String command = sc.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")) {
                String listReply = "";
                for (int i = 0; i < numTasks; i++) {
                    listReply += (i + 1) + ". " + taskList[i];
                    if (i != numTasks - 1)
                        listReply += "\n\t";
                }
                printReply(listReply);
            }
            else {
                addToList(command, taskList, numTasks);
                numTasks++;
            }
            command = sc.next();
        }
        printReply("Bye. Hope to see you again soon!");
    }
    public static void printReply(String reply){
        System.out.println("\t________________________________________\n\t" + reply
                           + "\n\t________________________________________");
    }
    public static void addToList(String task, String[] taskList, Integer numTasks){
        taskList[numTasks] = task;
        printReply("added: " + task);
    }
}
