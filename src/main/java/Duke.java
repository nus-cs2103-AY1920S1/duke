import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printWithIndentation(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        List<String> data = new ArrayList<>();
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.matches("bye")) {
                printWithIndentation("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.matches("list")) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < data.size(); i++) {
                    if(i != 0)
                        builder.append("\n" + "     ");
                    builder.append(i+1).append(". ").append(data.get(i));
                }
                printWithIndentation(builder.toString());
            } else {
                data.add(userInput);
                printWithIndentation("added: " + userInput);
            }
        }
    }
}
