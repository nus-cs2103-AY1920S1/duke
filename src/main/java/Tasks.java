import java.util.List;
import java.util.ArrayList;

public class Tasks {
    private List<String> tasks;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public void add(String task) {
        tasks.add(task);
        System.out.println("     ------------------------------------------------------------");
        System.out.println("     \u2795  " + task);
        System.out.println("     ------------------------------------------------------------");
    }

    public void list() {
        int i = 1;
        System.out.println("    ============================================================");
        for(String task : tasks) {
            System.out.println("     " + i + ". " + task);
            i++;
        }
        System.out.println("    ============================================================");
    }
}
