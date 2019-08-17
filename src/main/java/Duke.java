import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;

public class Duke {

    private static String stringFormatter(String s, String line) {
        String newString = line
                + String.format("\t %s\n", s)
                + line;
        return newString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String line = "\t____________________________________________________________\n";
        String greeting = line
                + "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + line;
        System.out.println(greeting);
        while (!sc.hasNext("bye")) {
            String s = sc.nextLine();
            switch (s) {
                case "list":
                    String stringlist = line
                            + IntStream
                            .range(0, list.size())
                            .mapToObj(i -> {
                                return "\t " + String.format("%d. %s", i + 1, list.get(i)) + "\n";
                            })
                            .reduce((x,y) -> x + y)
                            .get()
                            + line;
                    System.out.println(stringlist);
                    break;
                default:
                    list.add(s);
                    System.out.println(stringFormatter("added: " + s, line));

            }
        }
        System.out.println(stringFormatter("Bye. Hope to see you again soon!", line));
    }
}
