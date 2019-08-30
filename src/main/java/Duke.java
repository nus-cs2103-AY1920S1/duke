import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String blankLines = "____________________________________________________________";
        System.out.println("    Hello from\n" + logo);
        System.out.println(blankLines);
        System.out.println("    Hello, I'm Duke!\n    What can I do for you?");
        System.out.println(blankLines);

        ArrayList<String> myList = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();


        while(!command.equals("bye")){
            switch(command){
                case "list":
                    System.out.println(blankLines);
                    if (myList.isEmpty()){
                        System.out.println("    List is empty");
                    } else {
                        int index = 1;
                        for (String x : myList) {
                            System.out.println("    " + index + "." + x);
                            index++;
                        }
                    }
                    command = input.nextLine();
                    break;

                case "blah":
                    System.out.println(blankLines);
                    System.out.println("    blah");
                    command = input.nextLine();
                    break;

                default:
                    System.out.println(blankLines);
                    System.out.println("    added: " + command);
                    myList.add(command);
                    command = input.nextLine();
                    break;
            }
        }
        System.out.println("    Bye. Hope to see you again!");
    }
}
