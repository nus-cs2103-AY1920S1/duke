import java.util.*;

public class Duke {
    public static final String indent = "    ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        List<Task> history = new ArrayList<>();

        System.out.println(wrap(intro));
        String input;
        String output;
        while(!(input = sc.nextLine()).equals("bye")) {
            switch(input) {
                case "list":
                    output = historyToString(history);
                    break;
                default:
                    history.add(new Task(input));
                    output = "added: " + input;
            }
            System.out.println(wrap(output));
        };

        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(wrap(endMessage));
    }

    private static String historyToString(List<Task> history) {
        StringJoiner sj = new StringJoiner("\n");
        for(int i = 0; i < history.size(); i++) {
            sj.add((i + 1) + ". " + history.get(i));
        }
        return sj.toString();
    }

    private static String wrap(String str) {
        String wrappedString = "____________________________________________________________\n"
                + str
                + "\n" + "____________________________________________________________";
        String[] indentedStrings = Arrays.stream(wrappedString.split("\n")).map(s -> indent + s).toArray(String[]::new);

        return String.join("\n", indentedStrings);
    }
}
