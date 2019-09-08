import java.util.ArrayList;

public class TaskList {

    ArrayList<ListItem> lst = new ArrayList<>();

    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < this.lst.size(); i++) {
            toReturn = toReturn.concat((i + 1) + "." + this.lst.get(i).toString() + "\n     ");
        }

        return toReturn.substring(0, toReturn.length() - 6);
    }
}
