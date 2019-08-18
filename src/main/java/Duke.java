import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasksList = new ArrayList<>();
        while(sc.hasNextLine()){
            String nextItem = sc.next();
            if(nextItem.equals("list")){
                printList(tasksList);
            } else if (nextItem.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (nextItem.equals("done")){
                int taskID = sc.nextInt();
                Task doneTask = tasksList.get(taskID - 1);
                doneTask.setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(doneTask);
            } else {
                String currLine = nextItem + sc.nextLine();
                Task newTask = new Task(currLine, false);
                tasksList.add(newTask);
                System.out.println("added: " + newTask);
            }
        }
    }

    static void printList(ArrayList<Task> tasksList){
        int length = tasksList.size();
        for (int i = 0; i < length; i++) {
            int toPrint = i + 1;
            System.out.println(toPrint + ". " + tasksList.get(i));
        }
    }
}