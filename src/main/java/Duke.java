import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        while(reader.hasNext()) {
            String inputLine = reader.next();
            StringBuilder lineBuilder = new StringBuilder("     ");
            for(int i = 0; i < 59; i++) {
                lineBuilder.append("_");
            }
            if(!inputLine.equals("bye")) {
                StringBuilder temp = new StringBuilder("     ");
                temp.append(inputLine);
                System.out.println(lineBuilder.toString());
                System.out.println(temp.toString());
                System.out.println(lineBuilder.toString());
            } else {
                System.out.println(lineBuilder.toString());
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(lineBuilder.toString());
                break;
            }
        }
    }
}
