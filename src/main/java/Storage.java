import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<String> list;

    Storage() {
        this.list = new ArrayList<>();
    }

    List<String> getList() {
        return this.list;
    }

    void add(String item) {
        this.list.add(item);
    }
}
