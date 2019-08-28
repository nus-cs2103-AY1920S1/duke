import java.util.Date;

public class StorageItem {
    private String data;

    public StorageItem(String type, boolean isDone, String description) {
        data = String.format("%s | %d | %s", type, isDone ? 1 : 0, description);
    }

    public StorageItem(String type, boolean isDone, String description, Date datetime) {
        data = String.format("%s | %d | %s | %s", type, isDone ? 1 : 0, description, Consts.DATE_TIME_FORMATTER.format(datetime));
    }

    public String getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return data;
    }
}
