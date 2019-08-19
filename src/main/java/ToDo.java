public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public String toString() {
        String t = String.format("[T][%s]%s",
                this.getStatusIcon(), this.description);
        return t;
    }
}
