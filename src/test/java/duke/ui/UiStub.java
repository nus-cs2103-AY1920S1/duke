package duke.ui;

import duke.DukeExceptions;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

//custom ui stub class for changing output stream or using dummy scanner for testing purposes
public class UiStub extends Ui {
    private PrintStream testInputStream;
    private PrintStream testPrintStream;
    private final PrintStream originalPrintStream = System.out;

    /**
     * Constructor of UiStub that initializes a scanner with a dummy InputStream.
     * Outputs ui output to the print stream provided.
     *
     * @param testPrintStream Output print stream to use.
     */
    public UiStub(PrintStream testPrintStream) {
        this(
                new Scanner(
                        new InputStream() {
                            @Override
                            public int read() {
                                return 0;
                            }
                        }),
                testPrintStream);
    }

    public UiStub(Scanner scanner, PrintStream testPrintStream) {
        super(scanner);
        this.testPrintStream = testPrintStream;
    }

    @Override
    public void displayDukeException(DukeExceptions ex) {
        System.setOut(testPrintStream);
        super.displayDukeException(ex);
        System.setOut(originalPrintStream);
    }

    @Override
    public void printGreetingMsg() {
        System.setOut(testPrintStream);
        super.printGreetingMsg();
        System.setOut(originalPrintStream);
    }

    @Override
    public void printMsgLine(String msg) {
        System.setOut(testPrintStream);
        super.printMsgLine(msg);
        System.setOut(originalPrintStream);
    }

    @Override
    public void printEmptyLine() {
        System.setOut(testPrintStream);
        super.printEmptyLine();
        System.setOut(originalPrintStream);
    }

    @Override
    public void printLineDivider() {
        System.setOut(testPrintStream);
        super.printLineDivider();
        System.setOut(originalPrintStream);
    }
}
