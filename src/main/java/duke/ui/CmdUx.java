package duke.ui;

/**
 * Provides Command Line Interface elements.
 * Makes printing easier
 */

public class CmdUx {

    /**
     * Prints a message wrapped by horizontal bars.
     * @param str The message
     */
    public static void printHBars(String str) {
        String horizontalBar = "    ____________________________________________________________";
        String[] strSplit = str.split("\n");
        System.out.println(horizontalBar);
        for (String line : strSplit) {
            System.out.println("    " + line);
        }
        System.out.println(horizontalBar);
    }

}
