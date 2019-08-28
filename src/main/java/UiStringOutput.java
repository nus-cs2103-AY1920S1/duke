import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiStringOutput extends Ui {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    UiStringOutput() {
        super();
        output = new PrintStream(outputStream);
    }

    public String flushBuffer() {
        final String contents = outputStream.toString();
        outputStream.reset();
        return contents;
    }
}
