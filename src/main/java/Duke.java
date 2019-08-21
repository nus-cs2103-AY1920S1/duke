import java.util.Scanner;

public class Duke {
    /**
     * This class tests for chatbot Duke.
     */
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Task t = new Task("read book");
        Task[] arr = new Task[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(sc.hasNext()) {
            String input = sc.next();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(input.equals("list")){ //go through the array
                System.out.println("Here are the tasks in your list: ");
                for(int j = 0; j < i; j++){
                    System.out.println((j+1) + "." + arr[j]);
                }
            } else if(input.equals("done")) {
                int num = sc.nextInt();
                System.out.println("Nice! I've marked this task as done: ");
                arr[num-1].markAsDone();
                System.out.println(arr[num]);
            } else {
                String activity = input + sc.nextLine();
                Task t = new Task(activity);
                arr[i] = t;
                i++;
                System.out.println("added: " + activity);
            }
        }
    }
}
