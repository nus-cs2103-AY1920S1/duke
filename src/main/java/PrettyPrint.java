package seedu.duke;

public class PrettyPrint {
    static String line = "--------------------------------------------";
    static String indent = "    ";

    public static void print(String output) {
        System.out.println(indent + output);
    }

    public static void printBlock(String[] outputs) {
        print(line);
        for (String output : outputs) {
            print(" " + output);
        }
        print(line);
    }

    public static void printBlock(String output) {
        print(line);
        print(" " + output);
        print(line);
    }
}
