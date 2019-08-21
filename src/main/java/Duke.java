import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final String lineSpace = "_______________________________";
        String startMessage = lineSpace + "\nHello! I'm Duke\nWhat can I do for you?" + lineSpace;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String userInput = sc.nextLine();
            if(userInput.equals("bye")){
                System.out.println(lineSpace + "\nBye. Hope to see you again soon!\n" + lineSpace);
                break;
            }
            System.out.println(lineSpace + "\n" + userInput + "\n" + lineSpace);
        }

    }
}
