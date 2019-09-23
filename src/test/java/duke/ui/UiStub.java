package duke.ui;

import java.util.function.Consumer;

public class UiStub implements Ui {
    protected String line;
    protected Consumer<String[]> testPrint;

    public UiStub(String line, Consumer<String[]> testPrint) {
        this.line = line;
        this.testPrint = testPrint;
    }

    public String readCommand() {
        return this.line;
    }

    public void showWelcome() {

    }

    public void print(String...lines) {
        testPrint.accept(lines);
    }
}
