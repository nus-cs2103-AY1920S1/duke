import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> list = new ArrayList<String>(100);

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while(!input.equals("bye")) {
            if(!input.equals("list")) {
                addList(input);
                input = br.readLine();
            } else {
                readList();
                input = br.readLine();
            }
        }

        exit();
    }

    private static void readList() {
        for(int i = 1;i <= list.size();i++) {
            System.out.println(i + ". " + list.get(i - 1));
        }
    }

    private static void addList(String input) {
        list.add(input);
        System.out.println("added: " + input);
    }


    private static void exit() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

}
