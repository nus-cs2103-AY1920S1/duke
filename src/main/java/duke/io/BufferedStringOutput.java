package duke.io;

public class BufferedStringOutput implements UiOutput {
    public static final String OOPS_PREFIX = "\u2639 OOPS!!! "; // ☹

    private StringBuilder sb = new StringBuilder();

    public void say(String text) {
        sb.append(text).append('\n');
    }

    public void oops(String text) {
        sb.append(OOPS_PREFIX).append(text).append('\n');
    }

    public String nextResponse() {
        String response = sb.toString();
        sb = new StringBuilder();
        return response;
    }
}
