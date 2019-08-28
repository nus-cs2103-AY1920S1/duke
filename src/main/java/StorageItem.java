public class StorageItem {
    private String data;

    public StorageItem(String type, boolean isDone, String description, String datetime) {
        if (type.equals("T")) {
            data = String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
        } else {
            data = String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, datetime);
        }
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return data;
    }
}
