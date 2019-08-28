public class MissingFileExeception extends DukeException {

    @Override
    public String toString() {
        return String.format("%s File cannot be found!", super.toString());
    }
}
