import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private String[] mylist;
    private int size;

    public Duke() {
        mylist = new String[100];
        size = 0;
        greet();
    }

    public void add(String str) {
        mylist[size] = str;
        size++;
        print("\tadded: "+str);
    }

    public void list() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            if(i != 0) sb.append("\n");
            sb.append("\t"+(i+1)+". "+mylist[i]);
        }
        print(sb.toString());
    }

    public void exit() {
        print("\tBye. Hope to see you again soon!");
    }

    private void print(String str) {
        System.out.println("\t____________________________________________________________");
        System.out.println(str);
        System.out.println("\t____________________________________________________________");
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        print("\tHello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String query = sc.next();
        while(!query.equals("bye")) {
            if(query.equals("list")) {
                duke.list();
            }else {
                duke.add(query);
            }
            query = sc.next();
        }
        duke.exit();
    }
}
