import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String text = sc.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                IntStream.rangeClosed(1, list.size()).forEach(x -> {
                    System.out.println(x + ". " + list.get(x - 1));
                });
            } else {
                System.out.println("added: " + text);
                list.add(text);
            }
            text = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
