package duke;

public class MainUi extends Ui {
    private String tmpBuf;

    @Override
    public void show(String str) {
        tmpBuf = str;
    }

    /**
     * Returns output of the last ui.show() call, then empties the buffer.
     *
     * @return String expected from last ui.show() call
     */
    public String getResponse() {
        String str = tmpBuf;
        tmpBuf = null;
        return str;
    }
}
