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
        String[] arr = new String[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if(input.equals("list")){
                for(int j = 0; j < i; j++){
                    System.out.println((j+1) + ". " + arr[j]);
                }
            } else {
                arr[i] = input;
                System.out.println("added: " + input);
                i++;
            }
        }
    }
}
