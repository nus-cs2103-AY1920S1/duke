public class DukeReply {
    public final boolean shouldExitLoop;
    public final boolean shouldSave;
    public final String dukeReplyString;

    public DukeReply(boolean shouldExitLoop, boolean shouldSave, String dukeReplyString) {
        this.shouldExitLoop = shouldExitLoop;
        this.shouldSave = shouldSave;
        this.dukeReplyString = dukeReplyString;
    }
}
