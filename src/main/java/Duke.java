import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?\n";
        List<String> history = new ArrayList<>();

        System.out.println(intro);
        String input;
        while(!(input = sc.nextLine()).equals("bye")) {
            switch(input) {
                case "list":
                    System.out.println(historyToString(history));
                default:
                    history.add(input);
                    System.out.println(wrap("added: " + input));
            }
        };

        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(wrap(endMessage));
    }

    private static String historyToString(List<String> history) {
        StringJoiner sj = new StringJoiner("\n");
        for(int i = 0; i < history.size(); i++) {
            sj.add((i + 1) + ". " + history.get(i));
        }
        return sj.toString();
    }

    private static String wrap(String str) {
        return "    ____________________________________________________________\n"
                + "    " + str
                + "\n    ____________________________________________________________";
    }
}
