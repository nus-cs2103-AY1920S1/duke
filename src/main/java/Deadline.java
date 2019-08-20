public class Deadline extends Task {
    private String byWhen;

    public Deadline(String name, String byWhen) {
        super(name);
        // to add the colon
        String arr[] = byWhen.split(" ", 2);
        this.byWhen = arr[0] + ": " + arr[1];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[D]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (" + byWhen + ")");
        return stringBuilder.toString();
    }
}
