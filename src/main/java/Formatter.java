import java.util.List;

public class Formatter {

    public Formatter() {}

    public String format(String s) {
        return "     " + s;
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printFormat(String s) {
        printLine();
        String[] lines = s.split("\n");
        for (String line: lines) {
            System.out.println(format(line));
        }
        printLine();
    }

    public void printFormat(String...strings) {
        printLine();
        for (String string: strings) {
            System.out.println(format(string));
        }
        printLine();
    }

    public void printFormat(List<String> strings) {
        int count = 1;
        for (String string: strings) {
            System.out.println(format(String.format("%d. %s", count, string)));
            count++;
        }
    }

    public void printFormat(String s, List<String> list) {
        printLine();
        System.out.println(format(s));
        printFormat(list);
        printLine();
    }
}
