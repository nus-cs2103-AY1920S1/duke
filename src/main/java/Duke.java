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
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner reader = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();

        StringBuilder lineBuilder = new StringBuilder("     ");
        for (int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        while (reader.hasNext()) {
            String inputLine = reader.nextLine();
            if (inputLine.equals("bye")) {
                System.out.println(lineBuilder.toString());
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(lineBuilder.toString());
                break;
            } else if (inputLine.equals("list")) {
                System.out.println(lineBuilder.toString());
                if (stringList.isEmpty()) {
                    System.out.println("Sorry, there are no tasks in the list");
                } else {
                    int counter = 1;
                    for (String string : stringList) {
                        System.out.println("     " + counter + ". " + string);
                    }
                }
                System.out.println(lineBuilder.toString());
            } else {  //all other commands
                stringList.add(inputLine);
                StringBuilder temp = new StringBuilder("     ");
                temp.append("added: ");
                temp.append(inputLine);
                System.out.println(lineBuilder.toString());
                System.out.println(temp.toString());
                System.out.println(lineBuilder.toString());
                System.out.println();
            }
        }
    }
}