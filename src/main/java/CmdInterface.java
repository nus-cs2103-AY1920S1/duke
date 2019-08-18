public class CmdInterface {
    public static void printHBars(String str) {
        String horizontalBar = "    ____________________________________________________________";
        String[] strSplit = str.split("\n");
        System.out.println(horizontalBar);
        for (String line: strSplit) {
            System.out.println("    " + line);
        }
        System.out.println(horizontalBar);
    }
}
