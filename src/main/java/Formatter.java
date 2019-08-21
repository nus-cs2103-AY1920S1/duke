public class Formatter {

    public Formatter() {}

    public String format(String s) {
        return "     " + s;
    }

    public void printLine() {
        System.out.println(format("____________________________________________________________"));
    }

    public void printFormat(String s) {
        String[] lines = s.split("\n");
        for (String line: lines) {
            System.out.println(format(line));
        }
    }
}
