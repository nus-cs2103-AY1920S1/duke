import java.util.ArrayList;

/**
 * TextList class. Instances represents lists of texts stored by user.
 */
public class TextList {
    /**
     * ArrayList to store string elements.
     */
    private ArrayList<String> list;

    /**
     * Constructor.
     */
    TextList() {
        list = new ArrayList<>();
    }

    /**
     * Adds string to list.
     * @param element string to be added to list.
     */
    void add(String element) {
        list.add(element);
    }

    /**
     * String representation of list (indented).
     * @return String representation.
     */
    @Override
    public String toString() {
        String indent = "     ";
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            strb.append(indent).append(i + 1).append(". ").append(list.get(i));
            if (i < list.size() - 1) {
                strb.append("\n");
            }
        }
        return strb.toString();
    }
}
