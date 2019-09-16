/**
 * A class representing Duke's responses based on User inputs.
 */
public class DukeReply {
    public final boolean shouldExitLoop;
    public final boolean shouldSaveTaskList;
    public final boolean shouldSaveNoteList;
    public final String dukeReplyString;

    /**
     * A constructor for a new <code>DukeReply</code>.
     * 
     * @param shouldExitLoop Whether or not the interaction loop should terminate and Duke should shutdown
     * @param shouldSaveTaskList Whether or not Duke should attempt the current state of the TaskList
     * @param shouldSaveNoteList Whether or not Duke should attempt to save the current state of the NoteList
     * @param dukeReplyString Duke's message to the user
     */
    public DukeReply(boolean shouldExitLoop, 
                     boolean shouldSaveTaskList, 
                     boolean shouldSaveNoteList,
                     String dukeReplyString) {
        this.shouldExitLoop = shouldExitLoop;
        this.shouldSaveTaskList = shouldSaveTaskList;
        this.shouldSaveNoteList = shouldSaveNoteList;
        this.dukeReplyString = dukeReplyString;
    }
}
