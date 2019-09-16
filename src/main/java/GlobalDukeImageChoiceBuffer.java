/**
 * A buffer where details about what the next image of Duke to be displayed should be.
 */
public class GlobalDukeImageChoiceBuffer {
    private static DukeImageChoice dukeImageChoice = DukeImageChoice.Neutral;

    /**
     * Returns the next image of Duke to be displayed.
     * 
     * @return The next image of Duke to be displayed.
     */
    public static DukeImageChoice getDukeImageChoice() {
        return dukeImageChoice;
    }

    /**
     * Sets the next image of Duke to be displayed.
     * 
     * @param nextImageToBeDisplayed The next image of Duke to be displayed.
     */
    public static void setDukeImageChoice(DukeImageChoice nextImageToBeDisplayed) {
        dukeImageChoice = nextImageToBeDisplayed;
    }
}