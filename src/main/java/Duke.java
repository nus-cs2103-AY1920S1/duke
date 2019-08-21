import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final String lineSpace = "_______________________________\n";
        String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println(startMessage);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList();
        while(sc.hasNext()){
            String userInput = sc.nextLine();
            if(userInput.equals("bye")){
                System.out.println(lineSpace + "Bye. Hope to see you again soon!\n" + lineSpace);
                break;
            }
            switch(userInput) {
                case "list":
                    System.out.print(lineSpace);
                    for(int i=0; i < list.size(); i++){
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                    System.out.print(lineSpace);
                    break;
                default:
                    list.add(userInput);
                    System.out.println(lineSpace + "added: " + userInput + "\n" + lineSpace);
                //Level-1 code
                //System.out.println(lineSpace + "\n" + userInput + "\n" + lineSpace);
            }
        }

    }
}
