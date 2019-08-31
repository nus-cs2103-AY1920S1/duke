public class LineInFileParseException extends DukeException {

    int lineCount;

    String line;

    public LineInFileParseException(int lineCount, String line) {
        super();
        this.lineCount = lineCount;
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public int getLineCount() {
        return lineCount;
    }
}
