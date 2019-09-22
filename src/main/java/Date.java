import java.text.SimpleDateFormat;

/**
 * Date representation of date string that's given
 */
public class Date{
    boolean exists = false;
    String precursor = "";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HHss");
    java.util.Date data;
    public Date(String dateString) {
        precursor = dateString.split(" ", 2)[0];
        System.out.println(precursor);
        System.out.println(dateString.split(" ", 2)[1]);
        if (!dateString.equals("  ")) {
            try {
                this.data = formatter.parse(dateString.split(" ", 2)[1]);
                this.exists = true;
            }
            catch (Exception e) {
                System.out.println("WrongDateFormat");
            }
        }
    }

    public java.util.Date getData() {
        return this.data;
    }

    public String format() {
        if (exists) {
            return precursor + " " + formatter.format(data);
        }
        else {
            return "NIL";
        }
    }

    @Override
    public String toString() {
        if (exists) {
            return "(" + precursor + " " + formatter.format(data) + ")";
        }
        else {
            return "  ";
        }
    }
}
