package bot.duke.ui;

/**
 * Provides Command Line Interface elements.
 * Makes printing easier
 */

public class CmdUx {

    private static GuiWindow guiWindow = null;

    public static void setGuiWindow(GuiWindow currGuiWindow) {
        guiWindow = currGuiWindow;
    }

    /**
     * Prints a message wrapped by horizontal bars.
     *
     * @param str The message
     */
    public static void printHBars(String str) {
        String horizontalBar = "    __________________________________________________________________________________";
        String[] strSplit = str.split("\n");
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalBar + "\n");
        for (String line : strSplit) {
            sb.append("    " + line + "\n");
        }
        sb.append(horizontalBar + "\n");
        sb.append("\n");
        guiWindow.addLabelToDialogBox(sb.toString());
    }

    public static void print(String str) {
        guiWindow.addLabelToDialogBox(str);
    }

}
