package exceptions;

public abstract class DukeException extends Exception {
    private String desc;

    DukeException(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
