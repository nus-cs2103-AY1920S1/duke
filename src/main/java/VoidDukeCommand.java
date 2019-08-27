import java.util.NoSuchElementException;

public class VoidDukeCommand extends NoSuchElementException {
    private static final long serialVersionUID = 1L;
    
    public VoidDukeCommand() {
        super("I can't do anything if you don't tell me what to do... \ud83d\ude2d");
    }
}