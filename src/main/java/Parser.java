public class Parser {
    public Parser() {}

    public int getCommand(String input) {
        String[] i = input.split("\\s");
        if (i[0].equals("bye")) {
            return 0;
        } else if (i[0].equals("list")) {
            return 1;
        } else if (i[0].equals("done")) {
            return 2;
        } else if (i[0].equals("delete")) {
            return 3;
        } else if (i[0].equals("todo")) {
            return 4;
        } else if (i[0].equals("deadline")) {
            return 5;
        } else if (i[0].equals("event")) {
            return 6;
        } else {
            return -1;
        }
    }
}
