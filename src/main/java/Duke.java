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
        String lines = "    ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<String>();
        while(sc.hasNext()) {
            String inputMessage = sc.nextLine();
            System.out.println(lines);
            switch (inputMessage) {
                case "list" :
                    for (int i = 1; i <= inputList.size(); i ++) {
                        System.out.println("     " + i + ". " + inputList.get(i - 1));
                    }
                    System.out.println(lines);
                    System.out.println();
                    break;
                case "bye" :
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println(lines);
                    System.exit(0);
                    break;
                default :
                    inputList.add(inputMessage);
                    System.out.println("     added: " + inputMessage);
                    System.out.println(lines);
                    System.out.println();
            }
        }
    }
}
