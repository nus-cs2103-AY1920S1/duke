public class Event extends Task {
    private String atTime;

    public Event(String name, String atTime) {
        super(name);
        // to add the colon
        String arr[] = atTime.split(" ", 2);
        this.atTime = arr[0] + ": " + arr[1];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[E]");
        stringBuilder.append(super.toString());
        stringBuilder.append(" (" + atTime + ")");
        return stringBuilder.toString();
    }
}
