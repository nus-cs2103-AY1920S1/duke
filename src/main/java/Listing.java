import java.util.ArrayList;

public class Listing {
    private ArrayList<String> list;

    public Listing() {
        this.list = new ArrayList<>();
    }

    public void add(String msg) {
        list.add(msg);
        System.out.println(String.format("added: %s", msg));
    }

    public void listing() {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
