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
        ArrayList<String> stringList = new ArrayList<>();
        while(sc.hasNextLine()){
            String nextLine = sc.nextLine();
            if(nextLine.equals("list")){
                printList(stringList);
            } else if (nextLine.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                stringList.add(nextLine);
                System.out.println("added: " + nextLine);
            }
        }
    }
    
    static void printList(ArrayList<String> stringList){
        int length = stringList.size();
        for (int i = 0; i < length; i++) {
            int toPrint = i + 1;
            System.out.println(toPrint + ". " + stringList.get(i));
        }
    }
}
