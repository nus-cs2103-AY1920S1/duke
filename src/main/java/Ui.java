import java.io.*;

/**
 * A Class to handle UI.
 */
public class Ui {
    // iostreams
    private InputStream inStream;
    private BufferedReader in;
    private OutputStream outStream;
    private Writer out;

    /**
     * Creates a UI Class with input stream and output stream
     * @param inStream input stream
     * @param outStream output stream
     */
    private Ui(InputStream inStream, OutputStream outStream){
        this.inStream = inStream;
        this.in = new BufferedReader(new InputStreamReader(inStream));
        this.outStream = outStream;
        this.out = new PrintWriter(outStream);
    }

    /**
     * Creates a new UI Class with input stream and output stream
     * @param inStream input stream
     * @param outStream output stream
     * @return new UI object
     */
    public static Ui newUi(InputStream inStream, OutputStream outStream) {
        return new Ui(inStream, outStream);
    }

    /**
     * Closes the streams associated with this UI.
     */
    public void close() {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e1) {

        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e2) {

        }
    }

    /**
     * Writes to output stream.
     * @param line String to write
     * @throws IOException
     */
    public void write(String line) throws IOException {
        out.write(line);
    }

    /**
     * Flushes output stream.
     * @throws IOException
     */
    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Reads a line from input stream.
     * @return String read from input stream
     * @throws IOException
     */
    public String readLine() throws IOException {
        return in.readLine();
    }
}
