public class Event extends TaskWithDate {

    public Event(String description, String date) {
        super(description, date);
    }

    public static Event buildFromLine(String line) {
        String data[] = line.split(" /at ");
        if (data.length > 1) {
            if (data[1].length() > 0)
                return new Event(data[0], data[1]);
            else
                return null;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}