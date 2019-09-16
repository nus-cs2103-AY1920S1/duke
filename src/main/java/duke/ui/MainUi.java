package duke.ui;

//@@adapted from CarbonGrid. Self-Review: With GUI, handleUserInput takes the response String and prints it in GUI.
//  Ui Class presently prints to terminal with ui.show(). In order to not rewrite Ui class, MainUi childclass
//  is created to pass intended show() messages from Commands to handleUserInput, by overriding ui.show()

/**
 * MainUi childclass of Ui for GUI. All output strings are to be stored in buffer.
 *   getResponse() returns str to be printed and clears buffer.
 */
public class MainUi extends Ui {
    String buffer;

    @Override
    public void show(String temp) {
        buffer = temp;
    }

    @Override
    public void showWelcome() {
        show("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Returns str kept in buffer and clears it afterwards.
     * @return str to be displayed
     */
    public String getResponse() {
        String str = buffer;
        buffer = null;
        return str;
    }
}
//@@author