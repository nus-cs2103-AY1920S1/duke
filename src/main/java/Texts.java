import java.util.ArrayList;

class Texts {
    private ArrayList<String> Texts = new ArrayList<>();
    private int counter = 0;
    public void add(String text) {
        counter++;
        Texts.add(counter + ". " + text);
    }
    public void print() {
        for (String t : Texts) {
            System.out.println(t);
        }

    }
}
