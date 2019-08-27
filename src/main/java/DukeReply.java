/**
 * A class representing Duke's responses based on User inputs.
 */
public class DukeReply {
    public final boolean shouldExitLoop;
    public final boolean shouldSave;
    public final String dukeReplyString;

    /**
     * A constructor for a new <code>DukeReply</code>.
     * 
     * @param shouldExitLoop Whether or not the interaction loop should terminate and Duke should shutdown
     * @param shouldSave Whether or not Duke should attempt to save the state of serializable properties
     * @param dukeReplyString Duke's message to the user
     */
    public DukeReply(boolean shouldExitLoop, boolean shouldSave, String dukeReplyString) {
        this.shouldExitLoop = shouldExitLoop;
        this.shouldSave = shouldSave;
        this.dukeReplyString = dukeReplyString;
    }
}
