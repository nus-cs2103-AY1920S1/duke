package duke.utils;

public class UiResponse {
    private StringBuilder sb;

    public UiResponse() {
        this.sb = new StringBuilder();
    }

    public void reset() {
        this.sb = new StringBuilder();
    }

    public void addSentence(String s) {
        this.sb.append(s+"\n");
    }

    public String getResponse() {
        return this.sb.toString();
    }
}
